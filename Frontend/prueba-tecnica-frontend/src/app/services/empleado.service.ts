import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Empleado } from '../models/empleado';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private readonly API_URL = `${environment.apiUrl}/empleados`;

  constructor(private http: HttpClient) { }

  obtenerEmpleados(): Observable<Empleado[]> {
    return this.http.get<Empleado[]>(this.API_URL);
  }

  crearEmpleado(empleado: Empleado): Observable<Empleado> {
    return this.http.post<Empleado>(this.API_URL, empleado);
  }

  obtenerEmpleadoPorId(id: number): Observable<Empleado> {
    return this.http.get<Empleado>(`${this.API_URL}/${id}`);
  }

  actualizarEmpleado(id: number, empleado: Empleado): Observable<Empleado> {
    return this.http.put<Empleado>(`${this.API_URL}/${id}`, empleado);
  }
  
  eliminarEmpleado(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
  
}
