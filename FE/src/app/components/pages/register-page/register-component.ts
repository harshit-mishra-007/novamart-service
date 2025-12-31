import { CommonModule } from '@angular/common';
import { Component, CUSTOM_ELEMENTS_SCHEMA, inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../constants/user-constants';
import { LoginService } from '../../services/login.service';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    templateUrl: './register-component.html',
    styleUrls: ['./register-component.scss'],
})
export class RegisterComponent implements OnInit {
    private readonly registerService = inject(LoginService);
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
            confirmPassword: [''],
            userType: ['', [Validators.required]],
            street: ['', [Validators.required, Validators.minLength(3)]],
            city: ['', [Validators.required, Validators.minLength(3)]],
            state: ['', [Validators.required, Validators.minLength(2)]],
            country: ['', [Validators.required, Validators.minLength(2)]],
            zipCode: ['', [Validators.required]]
        });
    }

    validateForm(): void {
        this.errorMessage = '';
        this.successMessage = '';
        this.authForm.reset();
        this.authForm.get('username')?.setValidators([Validators.required, Validators.minLength(3)]);
        this.authForm.get('confirmPassword')?.setValidators([Validators.required]);
        this.authForm.get('password')?.setValidators([Validators.required, Validators.minLength(6)]);
        this.authForm.setValidators(this.passwordMatchValidator);
        this.authForm.get('username')?.updateValueAndValidity();
        this.authForm.get('confirmPassword')?.updateValueAndValidity();
        this.authForm.get('password')?.updateValueAndValidity();
    }

    private passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
        const group = control as FormGroup;
        const password = group.get('password')?.value;
        const confirmPassword = group.get('confirmPassword')?.value;

        if (!password || !confirmPassword) {
            return null;
        }

        return password === confirmPassword ? null : { passwordMismatch: true };
    }

    onSubmit(): void {
        if (this.authForm.invalid) {
            this.authForm.markAllAsTouched();
            return;
        }
        this.isLoading = true;
        this.errorMessage = '';
        this.successMessage = '';
        this.handleRegister();
    }

    private handleRegister(): void {
        const { username, email, password, userType, street, city, state, country, zipCode } = this.authForm.value;

        const newUser = {
            username,
            email,
            password,
            role: userType === 'seller' ? 'SELLER' : 'BUYER',
            address: {
                street,
                city,
                state,
                country,
                zipCode
            }
        };

        this.registerService.register(newUser).subscribe({
            next: (user) => {
                this.successMessage = 'Registration successful! Redirecting to login...';
                this.isLoading = false;
                setTimeout(() => {
                    this.router.navigate(['/login']);
                }, 1500);
            },
            error: (error) => {
                this.errorMessage = error.error?.message || 'Registration failed. Please try again.';
                this.isLoading = false;
            }
        });
    }
}