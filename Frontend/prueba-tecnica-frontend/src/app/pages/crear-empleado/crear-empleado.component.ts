import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmpleadoService } from '../../services/empleado.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-crear-empleado',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './crear-empleado.component.html',
  styleUrl: './crear-empleado.component.css'
})
export class CrearEmpleadoComponent {

  empleadoForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private empleadoService: EmpleadoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.empleadoForm = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      genero: ['', Validators.required],
      numeroIdentificacion: ['', Validators.required],
    });
  }

  crearEmpleado(): void {
    if (this.empleadoForm.invalid) return;

    this.empleadoService.crearEmpleado(this.empleadoForm.value).subscribe({
      next: () => this.router.navigate(['/empleados']),
      error: err => console.error('Error al crear empleado', err)
    });
  }

}
