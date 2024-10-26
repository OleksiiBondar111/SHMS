import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onTest() {
    this.authService.testInterceptor('test', 'test')
      .then(() => console.log("Test Interceptor"))
      .catch(() => console.log("Error happened"))
  }
}
