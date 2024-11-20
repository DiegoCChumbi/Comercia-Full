﻿using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net.NetworkInformation;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;

namespace ComerziaWA
{
    public partial class login : System.Web.UI.Page
    {
        private EmpleadoBO boEmpleado;

        public login()
        {
            boEmpleado = new EmpleadoBO();
        }

        protected void Page_Load(object sender, EventArgs e) { }

        protected void btnIniciarSesion_Click(object sender, EventArgs e)
        {
            string cuenta = txtUsuario.Text.Trim();
            string contrasenha = txtContrasenha.Text.Trim();
            // Validación de entrada
            if (string.IsNullOrEmpty(cuenta) || string.IsNullOrEmpty(contrasenha))
            {
                lblError.Text = "Por favor, ingresa tu usuario y/o contraseña.";
                return;
            }

            try
            {
                // Llamar al método de verificación
                int idEmpleado = boEmpleado.verEmpleado(cuenta, contrasenha);

                if (idEmpleado != -1)
                {
                    // Guardar idEmpleado en la sesión
                    Session["idEmpleado"] = idEmpleado;
                    Session["rol"] = boEmpleado.devolverRol(idEmpleado);
                    Response.Redirect("index.aspx");
                }
                else
                {
                    // Autenticación fallida
                    lblError.Text = "Usuario o contraseña incorrectos.";
                }
            }
            catch (Exception ex)
            {
                // Manejo de errores
                lblError.Text = "Error al procesar la solicitud: " + ex.Message;
            }
        }
    }
}
