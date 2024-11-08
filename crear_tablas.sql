USE tarea_academica;

-- Eliminar tabla LineaDocumento
DROP TABLE IF EXISTS linea_documento;

-- Eliminar tabla Documento
DROP TABLE IF EXISTS documento;

-- Eliminar tabla ProductoAlmacenado
DROP TABLE IF EXISTS producto_almacenado;

-- Eliminar tabla Visita
DROP TABLE IF EXISTS visita;

-- Eliminar tabla Persona table (now includes STI for Persona, Empleado, Administrador, Trabajador, and Vendedor)
DROP TABLE IF EXISTS persona;

-- Eliminar tabla Empresa table (now includes STI for Empresa, Representante, Cliente, and Proveedor)
DROP TABLE IF EXISTS empresa;

-- Eliminar tabla Producto
DROP TABLE IF EXISTS producto;

-- Eliminar tabla Almacen
DROP TABLE IF EXISTS almacen;

-- Almacen table remains the same
CREATE TABLE almacen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Merged Empresa table using Single Table Inheritance
CREATE TABLE empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipo_industria VARCHAR(50) NOT NULL,
    -- Common fields for all company types
    company_type ENUM('client_company', 'provider_company'),

    -- Fields for Proveedor
    fecha_afiliacion DATE,
    ruc VARCHAR(50) UNIQUE,
    razon_social VARCHAR(100),
    calificacion DOUBLE,
    pais VARCHAR(50),

    -- Fields for Cliente
    fecha_registro DATE,
    fecha_ultima_compra DATE,

    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Merged Persona table using Single Table Inheritance
CREATE TABLE persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    -- Common fields for all person types
    person_type ENUM('employee_person', 'representative_person'),

    -- Fields for Empleado
    estado ENUM('ACTIVO', 'INACTIVO'),
    nombre_usuario VARCHAR(50),
    contrasenha VARCHAR(255),
    salario DOUBLE,
    fecha_contratacion DATE,
    -- Common fields for all employee types
    employee_type ENUM('admin_employee', 'warehouse_employee', 'sales_employee'),

    -- Empleado Fields For Administrador and TrabajadorDeAlmacen
    id_almacen INT,

    -- Empleado Fields For TrabajadorDeAlmacen
    licencia_montacarga BOOLEAN,

    -- Empleado Fields For Vendedor
    ingresos_ventas DOUBLE,
    porcentaje_comision DOUBLE,

    -- Fields for Representante
    id_empresa INT,

    FOREIGN KEY (id_almacen) REFERENCES almacen(id) ON DELETE SET NULL,
    FOREIGN KEY (id_empresa) REFERENCES empresa(id) ON DELETE SET NULL,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Visita table updated to reference Persona and Empresa tables directly
CREATE TABLE visita (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_vendedor INT,  -- Now references persona(id)
    fecha DATE NOT NULL,
    duracion TIME NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_vendedor) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente) REFERENCES empresa(id) ON DELETE SET NULL,

    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Producto table remains the same
CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    stock_minimo INT NOT NULL,

    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- ProductoAlmacenado table remains the same
CREATE TABLE producto_almacenado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT,
    id_almacen INT,
    fecha_almacenado DATE NOT NULL,
    stock_actual INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES producto(id) ON DELETE CASCADE,
    FOREIGN KEY (id_almacen) REFERENCES almacen(id) ON DELETE CASCADE,

    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Documento table updated to reference Persona table directly
CREATE TABLE documento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_empresa INT,
    estado ENUM('SOLICITUD', 'COTIZACION', 'ANULADO', 'PAGADO') NOT NULL,
    tipo ENUM('COMPRA', 'VENTA', 'FACTURA') NOT NULL,
    id_vendedor INT,  -- Now references persona(id)
    id_administrador INT,  -- Now references persona(id)
    id_trabajador_de_almacen INT,  -- Now references persona(id)
    FOREIGN KEY (id_empresa) REFERENCES empresa(id) ON DELETE CASCADE,
    FOREIGN KEY (id_vendedor) REFERENCES persona(id) ON DELETE SET NULL,
    FOREIGN KEY (id_administrador) REFERENCES persona(id) ON DELETE SET NULL,
    FOREIGN KEY (id_trabajador_de_almacen) REFERENCES persona(id) ON DELETE SET NULL,

    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- LineaDocumento table remains the same
CREATE TABLE linea_documento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_documento INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    FOREIGN KEY (id_documento) REFERENCES documento(id) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(id) ON DELETE CASCADE,

    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- -- Insertar datos en la tabla Almacen
-- INSERT INTO almacen (nombre, estado, descripcion) VALUES
-- ('Almacen Central', 'Activo', 'Almacen principal de la empresa'),
-- ('Almacen Secundario', 'Activo', 'Almacen secundario para productos especiales');

-- -- Insertar datos en la tabla Empresa
-- INSERT INTO empresa (nombre, direccion, telefono, email, tipo_industria, company_type, fecha_afiliacion, ruc, razon_social, calificacion, pais, fecha_registro, fecha_ultima_compra) VALUES
-- ('Empresa 1', 'Calle Principal 123', '123456789', 'empresa1@example.com', 'Servicios', 'provider_company', '2022-01-01', '12345678912', 'Proveedor 1', 4.5, 'Perú', '2022-02-01', '2024-10-01'),
-- ('Empresa 2', 'Calle Secundaria 456', '987654321', 'empresa2@example.com', 'Comercio', 'provider_company', '2023-01-01', '98765432112', 'Proveedor 2', 4.0, 'Colombia', '2023-02-01', '2024-10-02');

-- -- Insertar datos en la tabla Persona
-- -- Juan Pérez: Empleado (Administrador, TrabajadorDeAlmacen, Vendedor) y Representante
-- INSERT INTO persona (dni, nombre, telefono, correo, direccion, person_type, estado, nombre_usuario, contrasenha, salario, fecha_contratacion, id_almacen, licencia_montacarga, ingresos_ventas, porcentaje_comision, id_empresa) VALUES
-- ('12345678A', 'Juan Pérez', '987654321', 'juan.perez@example.com', 'Calle Falsa 123', null, 'ACTIVO', 'juanp', 'password123', 3000.00, '2022-01-15', 1, TRUE, 10000.00, 0.05, 1);

-- -- María Gómez: Representante
-- INSERT INTO persona (dni, nombre, telefono, correo, direccion, person_type, id_empresa) VALUES
-- ('23456789B', 'María Gómez', '987654322', 'maria.gomez@example.com', 'Calle Verdadera 456', 'representative_person', 2);

-- -- Luis Martínez: Empleado (administrador, vendedor)
-- INSERT INTO persona (dni, nombre, telefono, correo, direccion, person_type, estado, nombre_usuario, contrasenha, salario, fecha_contratacion, id_almacen, ingresos_ventas, porcentaje_comision) VALUES
-- ('34567890C', 'Luis Martínez', '987654323', 'luis.martinez@example.com', 'Calle Imaginaria 789', 'employee_person', 'ACTIVO', 'luism', 'password789', 3500.00, '2023-03-10', 2, 15000.00, 0.07);

-- -- Insertar datos en la tabla Visita
-- INSERT INTO visita (id_vendedor, fecha, duracion, id_cliente) VALUES
-- (1, '2024-10-01', '01:30:00', 1),
-- (3, '2024-10-15', '02:00:00', 2);

-- -- Insertar datos en la tabla Producto
-- INSERT INTO producto (nombre, precio, stock_minimo) VALUES
-- ('Producto A', 50.00, 10),
-- ('Producto B', 30.00, 5);

-- -- Insertar datos en la tabla ProductoAlmacenado
-- INSERT INTO producto_almacenado (id_producto, id_almacen, fecha_almacenado, stock_actual) VALUES
-- (1, 1, '2024-10-01', 100),
-- (2, 1, '2024-10-01', 50);

-- -- Insertar datos en la tabla Documento
-- INSERT INTO documento (id_empresa, estado, tipo, id_vendedor, id_administrador, id_trabajador_de_almacen) VALUES
-- (1, 'COTIZACION', 'VENTA', 1, 1, NULL),
-- (2, 'PAGADO', 'COMPRA', NULL, 3, 3);

-- -- Insertar datos en la tabla LineaDocumento
-- INSERT INTO linea_documento (id_documento, id_producto, cantidad, precio_unitario) VALUES
-- (1, 1, 2, 50.00),
-- (1, 2, 1, 30.00),
-- (2, 1, 3, 50.00);
