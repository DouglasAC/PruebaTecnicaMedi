import { Routes } from '@angular/router';
import { EmpleadosComponent } from './pages/empleados/empleados.component';
import { LoginComponent } from './pages/login/login.component';
import { CrearEmpleadoComponent } from './pages/crear-empleado/crear-empleado.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'empleados', component: EmpleadosComponent },
    { path: 'empleados/nuevo', component: CrearEmpleadoComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
];
