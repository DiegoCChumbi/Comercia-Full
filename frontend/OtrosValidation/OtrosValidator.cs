using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FluentValidation;

namespace OtrosValidation
{
    public class OtrosValidator : AbstractValidator<OtrosVali>
    {
        public OtrosValidator() 
        {
            // Validación para DNI: Si está vacío muestra "El DNI es obligatorio"
            RuleFor(v => v.Dni)
                .NotEmpty().WithMessage("El DNI es obligatorio.");

            // Validación para DNI: Si no está vacío valida longitud y formato
            RuleFor(v => v.Dni)
                .Length(8).WithMessage("El DNI debe tener 8 caracteres.")
                .Matches(@"^\d{8}$").WithMessage("El DNI debe contener solo números.")
                .When(v => !string.IsNullOrEmpty(v.Dni)); // Solo valida si el DNI no está vacío

            // Validación para Nombre Completo: Si está vacío muestra "El nombre completo es obligatorio"
            RuleFor(v => v.NombreCompleto)
                .NotEmpty().WithMessage("El nombre completo es obligatorio.");

            // Validación para Nombre Completo: Si no está vacío valida longitud
            RuleFor(v => v.NombreCompleto)
                .Length(5, 100).WithMessage("El nombre completo debe tener entre 5 y 100 caracteres.")
                .When(v => !string.IsNullOrEmpty(v.NombreCompleto)); // Solo valida si el nombre completo no está vacío

            // Validación para Teléfono: Si está vacío muestra "El teléfono es obligatorio"
            RuleFor(v => v.Telefono)
                .NotEmpty().WithMessage("El teléfono es obligatorio.");

            // Validación para Teléfono: Si no está vacío valida formato
            RuleFor(v => v.Telefono)
                .Length(9).WithMessage("El teléfono debe contener 9 dígitos.")
                .Matches(@"^\d{9}$").WithMessage("El teléfono debe contener solo números.")
                .When(v => !string.IsNullOrEmpty(v.Telefono)); // Solo valida si el teléfono no está vacío

            // Validación para Correo: Si está vacío muestra "El correo es obligatorio"
            RuleFor(v => v.Correo)
                .NotEmpty().WithMessage("El correo es obligatorio.");

            // Validación para Correo: Si no está vacío valida formato
            RuleFor(v => v.Correo)
                .EmailAddress().WithMessage("El correo electrónico no es válido.")
                .When(v => !string.IsNullOrEmpty(v.Correo)); // Solo valida si el correo no está vacío

            // Validación para Dirección: Si está vacío muestra "La dirección es obligatoria"
            RuleFor(v => v.Direccion)
                .NotEmpty().WithMessage("La dirección es obligatoria.");

            // Validación para Dirección: Si no está vacío valida longitud
            RuleFor(v => v.Direccion)
                .Length(10, 200).WithMessage("La dirección debe tener entre 10 y 200 caracteres.")
                .When(v => !string.IsNullOrEmpty(v.Direccion)); // Solo valida si la dirección no está vacía

            // Validación para Nombre de Usuario: Si está vacío muestra "El nombre de usuario es obligatorio"
            RuleFor(v => v.NombreUsuario)
                .NotEmpty().WithMessage("El nombre de usuario es obligatorio.");

            // Validación para Nombre de Usuario: Si no está vacío valida longitud
            RuleFor(v => v.NombreUsuario)
                .Length(5, 50).WithMessage("El nombre de usuario debe tener entre 5 y 50 caracteres.")
                .When(v => !string.IsNullOrEmpty(v.NombreUsuario)); // Solo valida si el nombre de usuario no está vacío

            // Validación para Contraseña: Si está vacío muestra "La contraseña es obligatoria"
            RuleFor(v => v.Contrasenha)
                .NotEmpty().WithMessage("La contraseña es obligatoria.");

            // Validación para Contraseña: Si no está vacío valida longitud mínima
            RuleFor(v => v.Contrasenha)
                .MinimumLength(6).WithMessage("La contraseña debe tener al menos 6 caracteres.")
                .When(v => !string.IsNullOrEmpty(v.Contrasenha)); // Solo valida si la contraseña no está vacía

            // Validación para Salario: Si está vacío muestra "El salario es obligatorio"
            RuleFor(v => v.Salario)
                .NotEmpty().WithMessage("El salario es obligatorio.");

            // Validación para Salario: Si no está vacío valida formato y valor mayor que 0
            RuleFor(v => v.Salario)
                .Matches(@"^\d+(\.\d+)?$").WithMessage("El salario debe ser un número válido.")
                .Must(s => double.TryParse(s, out double result) && result > 0)
                .WithMessage("El salario debe ser mayor que 0.")
                .When(v => !string.IsNullOrEmpty(v.Salario)); // Solo valida si el salario no está vacío

            // Validación para Fecha de Contratación: Si está vacío muestra "La fecha de contratación es obligatoria"
            RuleFor(v => v.FechaContratacion)
                .NotEmpty().WithMessage("La fecha de contratación es obligatoria.");

            // Validación para Fecha de Contratación: Si no está vacío valida formato
            RuleFor(v => v.FechaContratacion)
                  .Must(date =>
                  {
                      // Intenta convertir la fecha al formato esperado (YYYY-MM-DD)
                      if (DateTime.TryParse(date, out DateTime parsedDate))
                      {
                          // Verifica que no sea una fecha futura y que el año sea mayor o igual a 1900
                          return parsedDate.Year >= 1900 && parsedDate <= DateTime.Now;
                      }
                      return false; // Si no se puede convertir, la validación falla
                  })
                .WithMessage("La fecha de contratación debe ser válida, no puede ser futura y el año debe ser mayor o igual a 1900.")
                .When(v => !string.IsNullOrEmpty(v.FechaContratacion)); // Solo valida si la fecha de contratación no está vacía

        }
    }
}
