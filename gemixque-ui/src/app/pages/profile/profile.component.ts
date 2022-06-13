import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { take } from 'rxjs';
import { UserService } from './services';
import { User } from './types';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  uuid: string;
  isLoading = false;
  user?: User;
  constructor(private readonly service: UserService, private readonly activatedRoute: ActivatedRoute) {
    this.uuid = this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.isLoading = true;
    this.service.getUser(this.uuid).pipe(take(1)).subscribe(data => {
      this.user = data;
      this.isLoading = false;
    });
  }
}
