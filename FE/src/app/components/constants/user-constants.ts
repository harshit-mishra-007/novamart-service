export interface User{
    id?: number;
    username: string;
    email: string;
    password?: string;
    role: string;
    address: {
        street: string;
        city: string;
        state: string;
        country: string;
        zipCode: string;
    }
}

export interface LoginRequest {
    email: string;
    password: string;
}

export interface RegisterRequest {
    username: string;
    email: string;
    password: string;
    role: string;
    address: {
        street: string;
        city: string;
        state: string;
        country: string;
        zipCode: string;
    }
}