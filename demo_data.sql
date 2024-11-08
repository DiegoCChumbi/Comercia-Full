-- Insertar datos en la tabla Almacen
INSERT INTO almacen (nombre, estado, descripcion) VALUES
('Almacen Central', 'Activo', 'Almacen principal de la empresa'),
('Almacen Secundario', 'Activo', 'Almacen secundario para productos especiales'),
('Almacen Norte', 'Activo', 'Almacen ubicado en la zona norte'),
('Almacen Sur', 'Activo', 'Almacen ubicado en la zona sur'),
('Almacen Este', 'Activo', 'Almacen ubicado en la zona este'),
('Almacen Oeste', 'Activo', 'Almacen ubicado en la zona oeste'),
('Almacen Central 2', 'Inactivo', 'Segundo almacen central'),
('Almacen Temporal', 'Activo', 'Almacen temporal para campañas'),
('Almacen Exportación', 'Activo', 'Almacen para productos de exportación'),
('Almacen Importación', 'Activo', 'Almacen para productos importados');

-- Insertar datos en la tabla Empresa
INSERT INTO empresa (nombre, direccion, telefono, email, tipo_industria, company_type, fecha_afiliacion, ruc, razon_social, calificacion, pais, fecha_registro, fecha_ultima_compra) VALUES
('Proveedor ABC', 'Av. Siempre Viva 123', '555123456', 'contacto@abc.com', 'Manufactura', 'provider_company', '2020-05-10', '20123456789', 'Proveedor ABC S.A.', 4.2, 'Perú', NULL, NULL),
('Proveedor XYZ', 'Calle Los Olivos 456', '555987654', 'info@xyz.com', 'Tecnología', 'provider_company', '2021-08-15', '20123456788', 'Proveedor XYZ S.A.', 4.8, 'Chile', NULL, NULL),
('Cliente Empresa 1', 'Jr. Las Flores 789', '555654321', 'ventas@empresa1.com', 'Comercio', 'client_company', NULL, NULL, NULL, NULL, NULL, '2021-03-20', '2024-09-30'),
('Cliente Empresa 2', 'Av. Los Pinos 321', '555321654', 'contacto@empresa2.com', 'Servicios', 'client_company', NULL, NULL, NULL, NULL, NULL, '2022-07-25', '2024-10-05'),
('Cliente Empresa 3', 'Calle Luna 654', '555789123', 'info@empresa3.com', 'Salud', 'client_company', NULL, NULL, NULL, NULL, NULL, '2023-01-10', '2024-09-28'),
('Proveedor DEF', 'Av. Mariscal 999', '555666777', 'contacto@def.com', 'Alimentos', 'provider_company', '2019-11-20', '20123456787', 'Proveedor DEF S.A.', 4.6, 'Argentina', NULL, NULL),
('Cliente Empresa 4', 'Jr. Sol 888', '555444555', 'ventas@empresa4.com', 'Educación', 'client_company', NULL, NULL, NULL, NULL, NULL, '2020-12-05', '2024-10-07'),
('Proveedor GHI', 'Av. Los Laureles 111', '555888999', 'contacto@ghi.com', 'Textil', 'provider_company', '2021-04-12', '20123456786', 'Proveedor GHI S.A.', 4.1, 'Ecuador', NULL, NULL),
('Cliente Empresa 5', 'Calle Los Jazmines 222', '555777666', 'ventas@empresa5.com', 'Automotriz', 'client_company', NULL, NULL, NULL, NULL, NULL, '2022-05-18', '2024-10-03'),
('Proveedor JKL', 'Av. Las Magnolias 333', '555222111', 'contacto@jkl.com', 'Construcción', 'provider_company', '2018-07-22', '20123456785', 'Proveedor JKL S.A.', 4.3, 'Brasil', NULL, NULL);

-- Insertar datos en la tabla Persona
-- Empleados
INSERT INTO persona (dni, nombre, telefono, correo, direccion, person_type, estado, nombre_usuario, contrasenha, salario, fecha_contratacion, employee_type, id_almacen, licencia_montacarga, ingresos_ventas, porcentaje_comision, id_empresa) VALUES
('11111111A', 'Carlos Ruiz', '555111222', 'carlos.ruiz@example.com', 'Av. Principal 123', 'employee_person', 'ACTIVO', 'cruiz', 'pass123', 2500.00, '2022-02-01', 'admin_employee', 1, NULL, NULL, NULL, NULL),
('22222222B', 'Ana Torres', '555333444', 'ana.torres@example.com', 'Calle Secundaria 456', 'employee_person', 'ACTIVO', 'atorres', 'pass456', 2200.00, '2021-06-15', 'warehouse_employee', 2, TRUE, NULL, NULL, NULL),
('33333333C', 'Miguel Sánchez', '555555666', 'miguel.sanchez@example.com', 'Jr. Terciario 789', 'employee_person', 'ACTIVO', 'msanchez', 'pass789', 2000.00, '2020-09-10', 'sales_employee', NULL, NULL, 50000.00, 0.03, NULL),
('44444444D', 'Laura Fernández', '555777888', 'laura.fernandez@example.com', 'Av. Los Robles 321', 'representative_person', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
('55555555E', 'Jorge Medina', '555999000', 'jorge.medina@example.com', 'Calle Las Palmas 654', 'representative_person', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4),
('66666666F', 'Sofía López', '555000111', 'sofia.lopez@example.com', 'Jr. Las Acacias 987', 'employee_person', 'INACTIVO', 'slopez', 'pass321', 1800.00, '2019-11-05', 'warehouse_employee', 3, FALSE, NULL, NULL, NULL),
('77777777G', 'Diego Castro', '555222333', 'diego.castro@example.com', 'Av. Los Álamos 654', 'employee_person', 'ACTIVO', 'dcastro', 'pass654', 2100.00, '2023-01-20', 'sales_employee', NULL, NULL, 30000.00, 0.04, NULL),
('88888888H', 'Elena García', '555444555', 'elena.garcia@example.com', 'Calle Las Rosas 321', 'representative_person', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('99999999I', 'Fernando Vega', '555666777', 'fernando.vega@example.com', 'Jr. Los Tulipanes 123', 'employee_person', 'ACTIVO', 'fvega', 'pass987', 2600.00, '2022-07-15', 'admin_employee', 2, NULL, NULL, NULL, NULL),
('10101010J', 'Ricardo Díaz', '555333222', 'ricardo.diaz@example.com', 'Jr. Los Claveles 555', 'employee_person', 'ACTIVO', 'rdiaz', 'pass147', 2300.00, '2023-04-01', 'warehouse_employee', 4, TRUE, NULL, NULL, NULL),
('11111111K', 'Patricia Herrera', '555444333', 'patricia.herrera@example.com', 'Av. Los Girasoles 666', 'representative_person', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7),
('12121212L', 'Andrés Molina', '555555444', 'andres.molina@example.com', 'Calle Los Cedros 777', 'employee_person', 'ACTIVO', 'amolina', 'pass258', 2400.00, '2021-08-30', 'sales_employee', NULL, NULL, 45000.00, 0.035, NULL),
('13131313M', 'Lucía Pérez', '555666555', 'lucia.perez@example.com', 'Jr. Los Naranjos 888', 'representative_person', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8),
('14141414N', 'David Romero', '555777666', 'david.romero@example.com', 'Av. Los Sauces 999', 'employee_person', 'ACTIVO', 'dromero', 'pass369', 2700.00, '2018-05-25', 'admin_employee', 3, NULL, NULL, NULL, NULL),
('15151515O', 'Marta Flores', '555888777', 'marta.flores@example.com', 'Calle Las Magnolias 111', 'employee_person', 'ACTIVO', 'mflores', 'pass159', 1900.00, '2020-12-12', 'warehouse_employee', 5, FALSE, NULL, NULL, NULL);

-- Insertar datos en la tabla Producto
INSERT INTO producto (nombre, precio, stock_minimo) VALUES
('Producto A', 50.00, 10),
('Producto B', 30.00, 5),
('Producto C', 20.00, 15),
('Producto D', 45.00, 8),
('Producto E', 60.00, 12),
('Producto F', 25.00, 20),
('Producto G', 35.00, 7),
('Producto H', 40.00, 9),
('Producto I', 55.00, 6),
('Producto J', 65.00, 11),
('Producto K', 70.00, 13),
('Producto L', 80.00, 14),
('Producto M', 90.00, 15),
('Producto N', 100.00, 16),
('Producto O', 110.00, 17),
('Producto P', 120.00, 18),
('Producto Q', 130.00, 19),
('Producto R', 140.00, 20),
('Producto S', 150.00, 21),
('Producto T', 160.00, 22);

-- Insertar datos en la tabla ProductoAlmacenado
INSERT INTO producto_almacenado (id_producto, id_almacen, fecha_almacenado, stock_actual) VALUES
(1, 1, '2024-10-01', 100),
(2, 1, '2024-10-01', 50),
(3, 2, '2024-09-15', 80),
(4, 2, '2024-09-20', 60),
(5, 3, '2024-09-25', 70),
(6, 3, '2024-09-30', 90),
(7, 4, '2024-10-05', 40),
(8, 4, '2024-10-10', 30),
(9, 5, '2024-10-15', 20),
(10, 5, '2024-10-20', 50),
(11, 6, '2024-10-22', 35),
(12, 6, '2024-10-24', 45),
(13, 7, '2024-10-26', 55),
(14, 7, '2024-10-28', 65),
(15, 8, '2024-10-30', 75),
(16, 8, '2024-11-01', 85),
(17, 9, '2024-11-03', 95),
(18, 9, '2024-11-05', 105),
(19, 10, '2024-11-07', 115),
(20, 10, '2024-11-09', 125);

-- Insertar datos en la tabla Documento
INSERT INTO documento (id_empresa, estado, tipo, id_vendedor, id_administrador, id_trabajador_de_almacen) VALUES
(3, 'COTIZACION', 'VENTA', 7, 1, NULL),
(4, 'PAGADO', 'COMPRA', NULL, 9, 2),
(5, 'SOLICITUD', 'VENTA', 3, NULL, NULL),
(6, 'ANULADO', 'FACTURA', NULL, 1, NULL),
(2, 'PAGADO', 'COMPRA', NULL, 9, 2),
(7, 'COTIZACION', 'VENTA', 12, 1, NULL),
(8, 'PAGADO', 'COMPRA', NULL, 14, 5),
(9, 'SOLICITUD', 'VENTA', 3, NULL, NULL),
(10, 'ANULADO', 'FACTURA', NULL, 1, NULL),
(5, 'PAGADO', 'COMPRA', NULL, 9, 2);

-- Insertar datos en la tabla LineaDocumento
INSERT INTO linea_documento (id_documento, id_producto, cantidad, precio_unitario) VALUES
(1, 1, 5, 50.00),
(1, 2, 3, 30.00),
(2, 3, 7, 20.00),
(2, 4, 2, 45.00),
(3, 5, 4, 60.00),
(3, 6, 6, 25.00),
(4, 7, 1, 35.00),
(4, 8, 2, 40.00),
(5, 9, 3, 55.00),
(5, 10, 2, 65.00),
(6, 11, 2, 70.00),
(6, 12, 4, 80.00),
(7, 13, 6, 90.00),
(7, 14, 8, 100.00),
(8, 15, 5, 110.00),
(8, 16, 3, 120.00),
(9, 17, 7, 130.00),
(9, 18, 2, 140.00),
(10, 19, 4, 150.00),
(10, 20, 6, 160.00);

-- Insertar datos en la tabla Visita
INSERT INTO visita (id_vendedor, fecha, duracion, id_cliente) VALUES
(7, '2024-10-01', '01:30:00', 3),
(3, '2024-10-05', '02:00:00', 4),
(7, '2024-10-10', '01:15:00', 5),
(3, '2024-10-15', '01:45:00', 6),
(7, '2024-10-20', '02:30:00', 3),
(3, '2024-10-25', '01:00:00', 4),
(12, '2024-10-27', '01:20:00', 7),
(7, '2024-10-29', '01:50:00', 8),
(12, '2024-11-02', '01:40:00', 5),
(3, '2024-11-04', '01:10:00', 6);

-- Continuar agregando más datos para aumentar el volumen

-- Agregar más empresas
INSERT INTO empresa (nombre, direccion, telefono, email, tipo_industria, company_type, fecha_afiliacion, ruc, razon_social, calificacion, pais, fecha_registro, fecha_ultima_compra) VALUES
('Cliente Empresa 6', 'Av. Las Camelias 444', '555999888', 'ventas@empresa6.com', 'Finanzas', 'client_company', NULL, NULL, NULL, NULL, NULL, '2022-03-14', '2024-10-08'),
('Proveedor MNO', 'Calle Los Manzanos 555', '555666555', 'contacto@mno.com', 'Transporte', 'provider_company', '2020-10-18', '20123456784', 'Proveedor MNO S.A.', 4.7, 'Uruguay', NULL, NULL),
('Cliente Empresa 7', 'Jr. Los Lirios 666', '555333444', 'ventas@empresa7.com', 'Agricultura', 'client_company', NULL, NULL, NULL, NULL, NULL, '2021-11-29', '2024-10-09');

-- Agregar más personas
INSERT INTO persona (dni, nombre, telefono, correo, direccion, person_type, estado, nombre_usuario, contrasenha, salario, fecha_contratacion, employee_type, id_almacen, licencia_montacarga, ingresos_ventas, porcentaje_comision, id_empresa) VALUES
('16161616P', 'Sara Núñez', '555111000', 'sara.nunez@example.com', 'Av. Las Orquídeas 121', 'employee_person', 'ACTIVO', 'snunez', 'pass753', 2200.00, '2020-06-07', 'warehouse_employee', 6, TRUE, NULL, NULL, NULL),
('17171717Q', 'Tomás Rojas', '555222111', 'tomas.rojas@example.com', 'Calle Los Álamos 212', 'employee_person', 'ACTIVO', 'trojas', 'pass852', 2000.00, '2021-09-19', 'sales_employee', NULL, NULL, 40000.00, 0.032, NULL),
('18181818R', 'Valeria Ortiz', '555333222', 'valeria.ortiz@example.com', 'Jr. Los Nogales 323', 'representative_person', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9);

-- Agregar más productos
INSERT INTO producto (nombre, precio, stock_minimo) VALUES
('Producto U', 170.00, 23),
('Producto V', 180.00, 24),
('Producto W', 190.00, 25),
('Producto X', 200.00, 26),
('Producto Y', 210.00, 27),
('Producto Z', 220.00, 28);

-- Agregar más producto almacenado
INSERT INTO producto_almacenado (id_producto, id_almacen, fecha_almacenado, stock_actual) VALUES
(21, 1, '2024-11-11', 135),
(22, 2, '2024-11-13', 145),
(23, 3, '2024-11-15', 155),
(24, 4, '2024-11-17', 165),
(25, 5, '2024-11-19', 175),
(26, 6, '2024-11-21', 185);

-- Agregar más documentos y líneas de documento
INSERT INTO documento (id_empresa, estado, tipo, id_vendedor, id_administrador, id_trabajador_de_almacen) VALUES
(9, 'COTIZACION', 'VENTA', 17, 1, NULL),
(10, 'PAGADO', 'COMPRA', NULL, 14, 5),
(11, 'SOLICITUD', 'VENTA', 3, NULL, NULL),
(12, 'ANULADO', 'FACTURA', NULL, 1, NULL);

INSERT INTO linea_documento (id_documento, id_producto, cantidad, precio_unitario) VALUES
(11, 21, 2, 170.00),
(11, 22, 4, 180.00),
(12, 23, 6, 190.00),
(12, 24, 8, 200.00),
(13, 25, 5, 210.00),
(13, 26, 3, 220.00);

-- Agregar más visitas
INSERT INTO visita (id_vendedor, fecha, duracion, id_cliente) VALUES
(17, '2024-11-06', '01:30:00', 9),
(3, '2024-11-08', '02:10:00', 10),
(17, '2024-11-10', '01:50:00', 11),
(3, '2024-11-12', '01:20:00', 12);
