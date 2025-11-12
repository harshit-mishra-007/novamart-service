# Novamart Service - Frontend Setup

## Overview

This Angular application is configured to work with the Novamart Spring Boot backend to manage products.

## Features

- ✅ Product list view with AG Grid
- ✅ Add new products
- ✅ View product details
- ✅ Edit existing products
- ✅ Delete products
- ✅ Basic authentication with backend
- ✅ Responsive design

## Prerequisites

- Node.js (v18 or higher)
- npm (v9 or higher)
- Angular CLI (v19 or higher)

## Installation

1. Navigate to the FE directory:

```bash
cd FE
```

2. Install dependencies:

```bash
npm install
```

## Running the Application

1. Make sure your backend is running on `http://localhost:8080`

2. Start the Angular development server:

```bash
npm start
```

or

```bash
ng serve
```

3. Open your browser and navigate to:

```
http://localhost:4200
```

## Default Credentials

The application uses Basic Authentication to connect to the backend:

- **Username**: admin
- **Password**: admin123

These credentials are configured in your backend's `application.yaml` file.

## Project Structure

```
FE/src/
├── app/
│   ├── components/
│   │   ├── product-list/         # Product listing with AG Grid
│   │   └── product-detail/        # Product form (view/edit/create)
│   ├── models/
│   │   ├── product.model.ts       # Product interface
│   │   └── api-response.model.ts  # API response wrapper
│   ├── services/
│   │   └── product.service.ts     # HTTP service for API calls
│   ├── app.component.*            # Root component
│   ├── app.config.ts              # Application configuration
│   └── app.routes.ts              # Route definitions
├── environments/
│   ├── environment.ts             # Development environment
│   └── environment.prod.ts        # Production environment
├── index.html
├── main.ts
├── polyfills.ts
└── styles.scss
```

## API Endpoints Used

- `GET /api/products` - Fetch all products
- `GET /api/products/{id}` - Fetch single product
- `POST /api/products` - Create new product (needs to be added to backend)
- `PUT /api/products/{id}` - Update product (needs to be added to backend)
- `DELETE /api/products/{id}` - Delete product (needs to be added to backend)

## Backend Integration

### Required Backend Endpoints

The frontend expects the following endpoints. You need to add POST, PUT, and DELETE to your ProductController:

```java
@PostMapping("products")
public ResponseEntity<ProductDetailsDto> createProduct(@RequestBody ProductDetailsDto productDto) {
    // Implementation needed
}

@PutMapping("products/{productId}")
public ResponseEntity<ProductDetailsDto> updateProduct(
    @PathVariable String productId,
    @RequestBody ProductDetailsDto productDto) {
    // Implementation needed
}

@DeleteMapping("products/{productId}")
public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
    // Implementation needed
}
```

### CORS Configuration

Your backend is already configured to allow requests from `http://localhost:4200` in the `application.yaml`:

```yaml
allowed-origins: ${ALLOWED_ORIGINS:http://localhost:4200, http://localhost:4201}
```

## Development

### Running Tests

```bash
npm test
```

### Building for Production

```bash
npm run build
```

The build artifacts will be stored in the `dist/` directory.

## Troubleshooting

### CORS Issues

If you see CORS errors:

1. Verify your backend's `allowed-origins` includes `http://localhost:4200`
2. Make sure your backend is running
3. Check the SecurityConfig in your backend

### Authentication Issues

If you get 401 Unauthorized:

1. Verify credentials in `product.service.ts` match your backend's `application.yaml`
2. Check if security is enabled: `security.enabled: true`

### Port Already in Use

If port 4200 is already in use:

```bash
ng serve --port 4201
```

Remember to update the backend's `allowed-origins` if you change the port.

## Next Steps

1. **Add Backend Endpoints**: Implement POST, PUT, DELETE methods in your ProductController
2. **Add Service Layer Logic**: Implement the create, update, delete methods in ProductServiceImpl
3. **Add Validation**: Add proper validation in both frontend and backend
4. **Error Handling**: Enhance error handling and user feedback
5. **Loading States**: Add loading indicators for better UX
6. **Unit Tests**: Write tests for components and services

## Technologies Used

- Angular 19.x
- TypeScript
- AG Grid Enterprise
- RxJS
- SCSS
- HTTP Basic Authentication
