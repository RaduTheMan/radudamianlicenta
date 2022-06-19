import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { forkJoin, take } from 'rxjs';
import { Review } from 'src/app/shared/types';
import { RecommendationsModalComponent } from './recommendations-modal';
import { UserService } from './services';
import { User } from './types';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {
  uuid: string;
  isLoading = false;
  pageGames = 1;
  pageReviews = 1;
  pageSize = 10;
  modalRef?: NgbModalRef;

  sources?: {
    user: User;
    gamesPlayedByUser: {uuid: string; title: string }[];
    reviewsMadeByUser: Review[]
  };
  constructor(
    private readonly service: UserService, 
    private readonly activatedRoute: ActivatedRoute, 
    private readonly modalService: NgbModal) {
    this.uuid = this.activatedRoute.snapshot.params['id'];
  }
  
  ngOnInit(): void {
    this.isLoading = true;
    forkJoin({
      user: this.service.getUser(this.uuid),
      gamesPlayedByUser: this.service.getGamesPlayedByUser(this.uuid),
      reviewsMadeByUser: this.service.getReviewsMadeByUser(this.uuid)
    }).pipe(take(1)).subscribe(data => {
      this.sources = data;
      this.isLoading = false;
    });
  }

  ngOnDestroy(): void {
    if (this.modalRef) {
      this.modalRef.close();
    }
  }

  open(): void {
    this.modalRef = this.modalService.open(RecommendationsModalComponent);
    this.modalRef.componentInstance.uuid = this.uuid;
  }
}
