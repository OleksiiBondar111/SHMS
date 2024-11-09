import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Observable} from "rxjs";
import {PatientDTO} from "../patient/patient-model";
import {logout} from "../auth/store/actions/auth.actions";
import {Store} from "@ngrx/store";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  patients$: Observable<PatientDTO[]> = this.authService.getAllPatients();

  constructor(private authService: AuthService, private store: Store) {
  }

  ngOnInit(): void {
  }

  onTest() {
    this.authService.testInterceptor('test', 'test')
      .then(() => console.log("Test Interceptor"))
      .catch(() => console.log("Error happened"))
  }

}
