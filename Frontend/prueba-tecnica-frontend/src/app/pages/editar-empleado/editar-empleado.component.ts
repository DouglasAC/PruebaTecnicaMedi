import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmpleadoService } from '../../services/empleado.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-empleado',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './editar-empleado.component.html',
  styleUrl: './editar-empleado.component.css'
})
export class EditarEmpleadoComponent {

  empleadoForm!: FormGroup;
  empleadoId!: number;

  constructor(
    private fb: FormBuilder,
    private empleadoService: EmpleadoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.empleadoId = Number(this.route.snapshot.paramMap.get('id'));
    this.empleadoForm = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      genero: ['', Validators.required],
      numeroIdentificacion: ['', Validators.required],
    });

    this.cargarEmpleado();
  }

  cargarEmpleado(): void {
    this.empleadoService.obtenerEmpleadoPorId(this.empleadoId).subscribe({
      next: (empleado) => {
        this.empleadoForm.patchValue({
          nombre: empleado.nombre,
          apellido: empleado.apellido,
          fechaNacimiento: empleado.fechaNacimiento,
          genero: empleado.genero,
          numeroIdentificacion: empleado.numeroIdentificacion,
        });
      },
      error: (err) => console.error('Error cargando empleado', err)
    });
  }

  actualizarEmpleado(): void {
    if (this.empleadoForm.invalid) return;

    this.empleadoService.actualizarEmpleado(this.empleadoId, this.empleadoForm.value)
      .subscribe({
        next: () => this.router.navigate(['/empleados']),
        error: (err) => console.error('Error actualizando empleado', err)
      });
  }

}
