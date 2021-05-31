public class Cita {

        private String dniMedico;
        private String dniPaciente;
        private String hora;
        private String diagnostico;
        private String receta;

        public Cita (String dniMedico, String dniPaciente, String hora, String diagnostico, String receta) {
            this.dniMedico = dniMedico;
            this.dniPaciente = dniPaciente;
            this.hora = hora;
            this.diagnostico = diagnostico;
            this.receta = receta;
        }
        public Cita (String dniMedico, String dniPaciente, String hora) {
            this.dniMedico = dniMedico;
            this.dniPaciente = dniPaciente;
            this.hora = hora;
            this.diagnostico = null;
            this.receta = null;
        }

        public String getDniMedico() {
            return dniMedico;
        }

        public void setDniMedico(String dniMedico) {
            this.dniMedico = dniMedico;
        }

        public String getDniPaciente() {
            return dniPaciente;
        }

        public void setDniPaciente(String dniPaciente) {
            this.dniPaciente = dniPaciente;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getDiagnostico() {
            return diagnostico;
        }

        public void setDiagnostico(String diagnostico) {
            this.diagnostico = diagnostico;
        }

        public String getReceta() {
            return receta;
        }

        public void setReceta(String receta) {
            this.receta = receta;
        }

        @Override
        public String toString() {
            return "Cita{" +
                    "DNI Médico ='" + dniMedico + '\'' +
                    ", DNI Paciente ='" + dniPaciente + '\'' +
                    ", Hora ='" + hora + '\'' +
                    ", Diagnóstico ='" + diagnostico + '\'' +
                    ", Receta ='" + receta + '\'' +
                    '}';
        }

}