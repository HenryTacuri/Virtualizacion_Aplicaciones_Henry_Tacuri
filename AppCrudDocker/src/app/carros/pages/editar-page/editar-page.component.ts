import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Carro } from '../../interfaces/carro.interface';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { CarrosServices } from '../../services/carros-services.service';
import { ModalService } from 'src/app/services/modal-service.service';

@Component({
  selector: 'app-editar-page',
  templateUrl: './editar-page.component.html',
  styleUrls: ['./editar-page.component.css']
})
export class EditarPageComponent implements OnInit {

  public mensaje!: string;
  public modalSwitch?: boolean;

  public carro!: Carro;

  public estado: boolean;

  public myForm: FormGroup = this.fb.group({
    codigo: [, []],
    marca: ['', [Validators.required, Validators.minLength(1)],],
    modelo: ['', [Validators.required, Validators.minLength(1)]],
    color: ['', [Validators.required, Validators.minLength(1)]],
    anio: ['', [Validators.required, Validators.min(1)]],
    cilindraje: ['', [Validators.required, Validators.min(1)]],
    precio: ['', [Validators.required, Validators.min(1)]],
  });

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private carrosServices: CarrosServices,
    private modalService: ModalService
  ) {
    this.estado = true;
  }

  ngOnInit(): void {
    if(this.estado) {
      this.modalService.modal.subscribe((valor) => {this.modalSwitch = valor});
      this.activatedRoute.params.pipe(
        switchMap(({cod}) => this.carrosServices.getCarroPorCodigo(cod))
      ).subscribe(resp => {
        this.carro = resp
        this.myForm.reset(this.carro)
      });
    } else {
      this.modalService.modal.subscribe((valor) => {this.modalSwitch = valor});
    }

  }


  public onSubmit() {
    if(this.myForm.invalid) {
      this.estado = false;
      this.mensaje = "Formulario Invalido";
      this.openModal();
      this.ngOnInit();
    } else {
      this.carro = this.myForm.value;
      this.estado = true;
      this.carrosServices.actualizarCarro(this.carro).subscribe(resp => {
        console.log({'estado': resp});
        this.mensaje = "Carro actualizado";
        this.openModal();
        this.ngOnInit();
      });

      this.myForm.reset();
    };


  }

  //Metodo para abrir el modal
  openModal() {
    this.modalSwitch = true;
  }

}


