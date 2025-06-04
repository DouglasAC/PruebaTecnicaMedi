import { Component } from '@angular/core';
import { Empleado } from '../../models/empleado';
import { EmpleadoService } from '../../services/empleado.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-empleados',
  imports: [CommonModule, RouterModule],
  templateUrl: './empleados.component.html',
  styleUrl: './empleados.component.css'
})
export class EmpleadosComponent {


  empleados: Empleado[] = [];
  cargando: boolean = true;

  constructor(private empleadoService: EmpleadoService) {}

  ngOnInit(): void {
    this.obtenerEmpleados();
  }

  obtenerEmpleados(): void {
   this.empleadoService.obtenerEmpleados().subscribe({
      next: (data) => {
        this.empleados = data;
        this.cargando = false;
      },
      error: (err) => {
        console.error('Error al obtener empleados', err);
        this.cargando = false;
      }
    });
  }


  eliminarEmpleado(id: number): void {
  if (confirm('¿Estás seguro de eliminar este empleado?')) {
    this.empleadoService.eliminarEmpleado(id).subscribe({
      next: () => {
        this.obtenerEmpleados();
      },
      error: (err) => console.error('Error al eliminar empleado', err)
    });
  }
}

}
