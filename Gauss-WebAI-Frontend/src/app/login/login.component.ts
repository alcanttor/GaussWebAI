import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
} from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass'],
})
export class LoginComponent implements OnInit {
  public username: string;
  public password: string;
  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {}

  login() {
    this.auth.login(this.username, this.password, () => {
      this.router.navigateByUrl('/home');
    });
  }
}
