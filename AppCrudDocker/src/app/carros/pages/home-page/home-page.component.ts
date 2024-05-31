import { Component, OnInit } from '@angular/core';
import { CarrosServices } from '../../services/carros-services.service';
import { Carro } from '../../interfaces/carro.interface';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  public carros: Carro[] = [];

  constructor(private carrosServices: CarrosServices) { }

  ngOnInit(): void {
    this.carrosServices.getCarros().subscribe(
      carros => this.carros = carros
    );
  }

  public buscar(codigo: string): void {
    this.carrosServices.getCarroPorCodigo(Number(codigo))
    .subscribe(
      resp => {
        this.carros = [];
        this.carros.push(resp);
        console.log({'carros': this.carros})
      }
    );
  }

}
