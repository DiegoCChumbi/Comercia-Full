USE ComerziaDB;

-- Merged Persona table using Single Table Inheritance
CREATE TABLE Persona (
    idPersona INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombreCompleto VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    tipoPersona ENUM('EMPLEADO', 'REPRESENTANTE', 'AMBOS') NOT NULL,

    -- Fields for Empleado
    estadoEmpleado ENUM('ACTIVO', 'INACTIVO'),
    nombreUsuario VARCHAR(50),
    contrasenha VARCHAR(255),
    salario DOUBLE,
    fechaContratacion DATE,
    esAdministrador BOOLEAN,
    esTrabajadorDeAlmacen BOOLEAN,
    esVendedor BOOLEAN,
    idAlmacen INT,  -- For Administrador and TrabajadorDeAlmacen
    licenciaMontacarga BOOLEAN,  -- For TrabajadorDeAlmacen
    ingresosVentas DOUBLE,  -- For Vendedor
    porcentajeComision DOUBLE,  -- For Vendedor

    -- Fields for Representante
    idEmpresa INT,

    FOREIGN KEY (idAlmacen) REFERENCES Almacen(idAlmacen) ON DELETE SET NULL,
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE SET NULL,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Almacen table remains the same
CREATE TABLE Almacen (
    idAlmacen INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Merged Empresa table using Single Table Inheritance
CREATE TABLE Empresa (
    idEmpresa INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipoIndustria VARCHAR(50) NOT NULL,
    tipoEmpresa ENUM('PROVEEDOR', 'CLIENTE', 'AMBOS') NOT NULL,

    -- Fields for Proveedor
    fecha_afiliacion DATE,
    RUC VARCHAR(50) UNIQUE,
    razonSocial VARCHAR(100),
    calificacion DOUBLE,
    pais VARCHAR(50),

    -- Fields for Cliente
    fechaRegistro DATE,
    fechaUltimaCompra DATE,

    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Visita table updated to reference Persona and Empresa tables directly
CREATE TABLE Visita (
    idVisita INT AUTO_INCREMENT PRIMARY KEY,
    idVendedor INT,  -- Now references Persona(idPersona)
    fecha DATE NOT NULL,
    duracion TIME NOT NULL,
    idCliente INT,
    FOREIGN KEY (idVendedor) REFERENCES Persona(idPersona) ON DELETE CASCADE,
    FOREIGN KEY (idCliente) REFERENCES Empresa(idEmpresa) ON DELETE SET NULL,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Producto table remains the same
CREATE TABLE Producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombreProducto VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    stockMinimo INT NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- ProductoAlmacenado table remains the same
CREATE TABLE ProductoAlmacenado (
    idProductoAlmacenado INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT,
    idAlmacen INT,
    fechaAlmacenado DATE NOT NULL,
    stockActual INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) ON DELETE CASCADE,
    FOREIGN KEY (idAlmacen) REFERENCES Almacen(idAlmacen) ON DELETE CASCADE,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Documento table updated to reference Persona table directly
CREATE TABLE Documento (
    idDocumento INT AUTO_INCREMENT PRIMARY KEY,
    idEmpresa INT,
    estado ENUM('SOLICITUD', 'COTIZACION', 'ANULADO', 'PAGADO') NOT NULL,
    tipo ENUM('COMPRA', 'VENTA', 'FACTURA') NOT NULL,
    idVendedor INT,  -- Now references Persona(idPersona)
    idAdministrador INT,  -- Now references Persona(idPersona)
    idTrabajadorDeAlmacen INT,  -- Now references Persona(idPersona)
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    FOREIGN KEY (idVendedor) REFERENCES Persona(idPersona) ON DELETE SET NULL,
    FOREIGN KEY (idAdministrador) REFERENCES Persona(idPersona) ON DELETE SET NULL,
    FOREIGN KEY (idTrabajadorDeAlmacen) REFERENCES Persona(idPersona) ON DELETE SET NULL,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- LineaDocumento table remains the same
CREATE TABLE LineaDocumento (
    idLinea INT AUTO_INCREMENT PRIMARY KEY,
    idDocumento INT,
    idProducto INT,
    cantidad INT NOT NULL,
    precioUnitario DOUBLE NOT NULL,
    FOREIGN KEY (idDocumento) REFERENCES Documento(idDocumento) ON DELETE CASCADE,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) ON DELETE CASCADE,
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Insertar datos en la tabla Persona
-- Juan Pérez: Empleado (Administrador, TrabajadorDeAlmacen, Vendedor) y Representante
INSERT INTO Persona (dni, nombreCompleto, telefono, correo, direccion, tipoPersona, estadoEmpleado, nombreUsuario, contrasenha, salario, fechaContratacion, esAdministrador, esTrabajadorDeAlmacen, esVendedor, idAlmacen, licenciaMontacarga, ingresosVentas, porcentajeComision, idEmpresa) VALUES
('12345678A', 'Juan Pérez', '987654321', 'juan.perez@example.com', 'Calle Falsa 123', 'AMBOS', 'ACTIVO', 'juanp', 'password123', 3000.00, '2022-01-15', TRUE, TRUE, TRUE, 1, TRUE, 10000.00, 0.05, 1);

-- María Gómez: Representante
INSERT INTO Persona (dni, nombreCompleto, telefono, correo, direccion, tipoPersona, idEmpresa) VALUES
('23456789B', 'María Gómez', '987654322', 'maria.gomez@example.com', 'Calle Verdadera 456', 'REPRESENTANTE', 2);

-- Luis Martínez: Empleado (Administrador, Vendedor)
INSERT INTO Persona (dni, nombreCompleto, telefono, correo, direccion, tipoPersona, estadoEmpleado, nombreUsuario, contrasenha, salario, fechaContratacion, esAdministrador, esTrabajadorDeAlmacen, esVendedor, idAlmacen, ingresosVentas, porcentajeComision) VALUES
('34567890C', 'Luis Martínez', '987654323', 'luis.martinez@example.com', 'Calle Imaginaria 789', 'EMPLEADO', 'ACTIVO', 'luism', 'password789', 3500.00, '2023-03-10', TRUE, FALSE, TRUE, 2, 15000.00, 0.07);

-- Insertar datos en la tabla Almacen
INSERT INTO Almacen (nombre, estado, descripcion) VALUES
('Almacen Central', 'Activo', 'Almacen principal de la empresa'),
('Almacen Secundario', 'Activo', 'Almacen secundario para productos especiales');

-- Insertar datos en la tabla Empresa
INSERT INTO Empresa (nombre, direccion, telefono, email, tipoIndustria, tipoEmpresa, fecha_afiliacion, RUC, razonSocial, calificacion, pais, fechaRegistro, fechaUltimaCompra) VALUES
('Empresa 1', 'Calle Principal 123', '123456789', 'empresa1@example.com', 'Servicios', 'AMBOS', '2022-01-01', '12345678912', 'Proveedor 1', 4.5, 'Perú', '2022-02-01', '2024-10-01'),
('Empresa 2', 'Calle Secundaria 456', '987654321', 'empresa2@example.com', 'Comercio', 'AMBOS', '2023-01-01', '98765432112', 'Proveedor 2', 4.0, 'Colombia', '2023-02-01', '2024-10-02');

-- Insertar datos en la tabla Visita
INSERT INTO Visita (idVendedor, fecha, duracion, idCliente) VALUES
(1, '2024-10-01', '01:30:00', 1),
(3, '2024-10-15', '02:00:00', 2);

-- Insertar datos en la tabla Producto
INSERT INTO Producto (nombreProducto, precio, stockMinimo) VALUES
('Producto A', 50.00, 10),
('Producto B', 30.00, 5);

-- Insertar datos en la tabla ProductoAlmacenado
INSERT INTO ProductoAlmacenado (idProducto, idAlmacen, fechaAlmacenado, stockActual) VALUES
(1, 1, '2024-10-01', 100),
(2, 1, '2024-10-01', 50);

-- Insertar datos en la tabla Documento
INSERT INTO Documento (idEmpresa, estado, tipo, idVendedor, idAdministrador, idTrabajadorDeAlmacen) VALUES
(1, 'COTIZACION', 'VENTA', 1, 1, NULL),
(2, 'PAGADO', 'COMPRA', NULL, 3, 3);

-- Insertar datos en la tabla LineaDocumento
INSERT INTO LineaDocumento (idDocumento, idProducto, cantidad, precioUnitario) VALUES
(1, 1, 2, 50.00),
(1, 2, 1, 30.00),
(2, 1, 3, 50.00);
