export interface CinemaCostReportDTO {
  idCine: number;
  nombreCine: string;
  fechasModificacion: Date[];
  costos: number[];
  costoTotal: number;
}
