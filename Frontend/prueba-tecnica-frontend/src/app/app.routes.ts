import { Routes } from '@angular/router';
import { EmpleadosComponent } from './pages/empleados/empleados.component';
import { LoginComponent } from './pages/login/login.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'empleados', component: EmpleadosComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
];
