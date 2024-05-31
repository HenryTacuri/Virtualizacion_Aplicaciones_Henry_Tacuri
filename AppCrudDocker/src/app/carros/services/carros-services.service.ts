import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Carro } from '../interfaces/carro.interface';
import { Observable, catchError, map, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarrosServices{

  private baseUrl: string = 'http://localhost:8080/demoappdocker';

  constructor(private http: HttpClient) { }

  public guardarCarro(carro: Carro): Observable<boolean>  {
    return this.http.post<Carro>(`${this.baseUrl}/rs/guardar`, carro)
    .pipe(
      map(resp => true),
      catchError(error => of(false))
    );
  }

  public actualizarCarro(carro: Carro): Observable<boolean> {
    return this.http.put<Carro>(`${this.baseUrl}/rs/actualizar`, carro)
    .pipe(
      map(resp => true),
      catchError(error => of(false))
    );
  }

  public eliminarCarro(codigo: number): Observable<boolean> {
    return this.http.delete(`${this.baseUrl}/rs/eliminar?codigo=${codigo}`)
    .pipe(
      map(resp => true),
      catchError(error => of(false))
    );
  }

  public getCarroPorCodigo(codigo: number): Observable<Carro> {
    return this.http.get<Carro>(`${this.baseUrl}/rs/getPorCodigo?codigo=${codigo}`)
  }

  public getCarros(): Observable<Carro[]> {
    return this.http.get<Carro[]>(`${this.baseUrl}/rs/getCarros`);
  }

}
