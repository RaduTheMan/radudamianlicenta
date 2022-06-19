import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ChartConfiguration, ChartData, ChartType } from 'chart.js';
import { take } from 'rxjs';
import { RecommendationsService } from './services';
import { Recommendation } from './types';

import DataLabelsPlugin from 'chartjs-plugin-datalabels';

@Component({
  selector: 'app-recommendations-modal',
  templateUrl: './recommendations-modal.component.html',
  styleUrls: ['./recommendations-modal.component.css']
})
export class RecommendationsModalComponent implements OnInit {
  @Input() uuid?: string;
  k = new FormControl(0, [Validators.min(1), Validators.max(50)]);
  isTableViewControl = new FormControl(true);
  isTableView = true;
  page = 1;
  pageSize = 10;
  recommendations?: Recommendation[];
  recommendationsOnPage?: Recommendation[];
  isLoading = false;

  kAttempts = new Set<number>();
  chartRegistry = new Map<string, number>();
  barChartType: ChartType = 'bar';
  barChartData?: ChartData<'bar'>;
  barChartPlugins = [ DataLabelsPlugin ];
  barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    scales: {
      x: { display: false },
      y: {
        min: 0,
        ticks: {
          stepSize: 1
        }
      }
    },
    plugins: {
      legend: {
        display: true,
      },
      datalabels: {
        anchor: 'end',
        align: 'end'
      }
    }
  };

  constructor(public activeModal: NgbActiveModal, private readonly service: RecommendationsService) { }

  ngOnInit(): void {
    this.isTableViewControl.valueChanges.subscribe(value => {
      this.isTableView = value;
    });
  }

  getRecommendations(): void {
    if (this.uuid){
      this.isLoading = true;
      this.service.getRecommendations(this.uuid, this.k.value).pipe(take(1)).subscribe(data => {
        this.recommendations = data;
        this.updateChartData(this.k.value);
        this.refreshRecommendations();
        this.isLoading = false;
      });
    }
  }

  refreshRecommendations(): void {
    if (this.recommendations) {
      this.recommendationsOnPage = this.recommendations.slice(
        (this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
    }
  }

  updateChartData(k: number): void {
    if (!this.kAttempts.has(k) && this.recommendations) {
      this.kAttempts.add(k);
      for (let recommendation of this.recommendations) {
        const value = this.chartRegistry.has(recommendation.title) ? this.chartRegistry.get(recommendation.title)! + 1 : 1;
        this.chartRegistry.set(recommendation.title, value);
      }
      const entries = Array.from(this.chartRegistry.entries());
      const sortedEntries = entries.sort((firstEntry, secondEntry) => {
        if (firstEntry[1] < secondEntry[1])
          return 1;
        if (firstEntry[1] > secondEntry[1])
          return -1;
        return 0; 
      });
      this.barChartData = {
        labels: sortedEntries.map(entry => entry[0]).slice(0, 10),
        datasets: [
          { data: sortedEntries.map(entry => entry[1]).slice(0, 10), label: 'Frequency' }
        ]
      };
    }
  }
}
