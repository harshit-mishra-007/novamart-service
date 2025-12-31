import { CommonModule } from '@angular/common';
import { Component, CUSTOM_ELEMENTS_SCHEMA, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from "../../constants/user-constants";
import { LoginService } from '../../services/login.service';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    templateUrl: './login-component.html',
    styleUrls: ['./login-component.scss'],
})
export class LoginComponent implements OnInit {
    private readonly loginService = inject(LoginService);
    private readonly fb = inject(FormBuilder);
    private readonly router = inject(Router);

    authForm!: FormGroup;
    isLoading = false;
    errorMessage = '';
    successMessage = '';

    constructor() {
    }

    ngOnInit(): void {
        this.initializeForm();
    }

    private initializeForm(): void {
        this.authForm = this.fb.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            username: [''],
            confirmPassword: ['']
        });
    }

    validateForm(): void {
        this.errorMessage = '';
        this.successMessage = '';
        this.authForm.reset();
        this.authForm.get('username')?.clearValidators();
        this.authForm.get('confirmPassword')?.clearValidators();
        this.authForm.get('password')?.setValidators([Validators.required]);
        this.authForm.get('username')?.updateValueAndValidity();
        this.authForm.get('confirmPassword')?.updateValueAndValidity();
        this.authForm.get('password')?.updateValueAndValidity();
    }

    onSubmit(): void {
        if (this.authForm.invalid) {
            this.authForm.markAllAsTouched();
            return;
        }

        this.isLoading = true;
        this.errorMessage = '';
        this.successMessage = '';
        this.handleLogin();
    }

    private handleLogin(): void {
        const { email, password } = this.authForm.value as LoginRequest;

        this.loginService.getUserByEmail(email).subscribe({
            next: (user) => {
                if (!user || !user.email) {
                    this.errorMessage = 'User not found. Redirecting to registration...';
                    this.isLoading = false;
                    setTimeout(() => {
                        this.router.navigate(['/register']);
                    }, 1500);
                    return;
                }
                if (user.password !== password) {
                    this.errorMessage = 'Invalid email or password.';
                    this.isLoading = false;
                    return;
                }
                this.successMessage = 'Login successful! Redirecting...';
                this.isLoading = false;
                localStorage.setItem('currentUser', JSON.stringify(user));
                setTimeout(() => {
                    this.router.navigate(['/products']);
                }, 1000);
            },
            error: (error) => {
                if (error.status === 404) {
                    this.errorMessage = 'User not found. Redirecting to registration...';
                    this.isLoading = false;
                    setTimeout(() => {
                        this.router.navigate(['/register']);
                    }, 1500);
                } else {
                    this.errorMessage = error.error?.message || 'Login failed. Please try again.';
                    this.isLoading = false;
                }
            }
        });
    }
}