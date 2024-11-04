use ComerziaDB;
CREATE TABLE Persona (
    idPersona INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombreCompleto VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Empleado (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    idPersona INT,
    estado ENUM('ACTIVO', 'INACTIVO') NOT NULL,
    nombreUsuario VARCHAR(50) NOT NULL,
    contrasenha VARCHAR(255) NOT NULL,
    salario DOUBLE NOT NULL,
    fechaContratacion DATE NOT NULL,
    FOREIGN KEY (idPersona) REFERENCES Persona(idPersona) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Almacen (
    idAlmacen INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Administrador (
    idAdministrador INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT,
    idAlmacen INT,
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado) ON DELETE CASCADE,
    FOREIGN KEY (idAlmacen) REFERENCES Almacen(idAlmacen) ON DELETE SET NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE TrabajadorDeAlmacen (
    idTrabajador INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT,
    licenciaMontacarga BOOLEAN NOT NULL,
    idAlmacen INT,
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado) ON DELETE CASCADE,
    FOREIGN KEY (idAlmacen) REFERENCES Almacen(idAlmacen) ON DELETE SET NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Vendedor (
    idVendedor INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT,
    ingresosVentas DOUBLE NOT NULL,
    porcentajeComision DOUBLE NOT NULL,
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Empresa (
    idEmpresa INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipoIndustria VARCHAR(50) NOT NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Representante (
    idRepresentante INT AUTO_INCREMENT PRIMARY KEY,
    idPersona INT,
    idEmpresa INT,
    FOREIGN KEY (idPersona) REFERENCES Persona(idPersona) ON DELETE CASCADE,
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Proveedor (
    idProveedor INT AUTO_INCREMENT PRIMARY KEY,
    idEmpresa INT,
    fecha_afiliacion DATE NOT NULL,
    RUC VARCHAR(50) NOT NULL UNIQUE,
    razonSocial VARCHAR(100) NOT NULL,
    calificacion DOUBLE,
    pais VARCHAR(50),
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    idEmpresa INT,
    fechaRegistro DATE NOT NULL,
    fechaUltimaCompra DATE NOT NULL,
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Visita (
    idVisita INT AUTO_INCREMENT PRIMARY KEY,
    idVendedor INT,
    fecha DATE NOT NULL,
    duracion TIME NOT NULL,
    idCliente INT,
    FOREIGN KEY (idVendedor) REFERENCES Vendedor(idVendedor) ON DELETE CASCADE,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) ON DELETE SET NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,          
    nombreProducto VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    stockMinimo INT NOT NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE ProductoAlmacenado (
    idProductoAlmacenado INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT,
    idAlmacen INT,
    fechaAlmacenado DATE NOT NULL,
    stockActual INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) ON DELETE CASCADE,
    FOREIGN KEY (idAlmacen) REFERENCES Almacen(idAlmacen) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Documento (
    idDocumento INT AUTO_INCREMENT PRIMARY KEY,
    idEmpresa INT,
    estado ENUM('SOLICITUD', 'COTIZACION', 'ANULADO', 'PAGADO') NOT NULL,
    tipo ENUM('COMPRA', 'VENTA', 'FACTURA') NOT NULL,
    idVendedor INT,
    idAdministrador INT,
    idTrabajadorDeAlmacen INT,
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    FOREIGN KEY (idVendedor) REFERENCES Vendedor(idVendedor) ON DELETE SET NULL,
    FOREIGN KEY (idAdministrador) REFERENCES Administrador(idAdministrador) ON DELETE SET NULL,
    FOREIGN KEY (idTrabajadorDeAlmacen) REFERENCES TrabajadorDeAlmacen(idTrabajador) ON DELETE SET NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE LineaDocumento (
    idLinea INT AUTO_INCREMENT PRIMARY KEY,
    idDocumento INT,
    idProducto INT,
    cantidad INT NOT NULL,
    precioUnitario DOUBLE NOT NULL,
    FOREIGN KEY (idDocumento) REFERENCES Documento(idDocumento) ON DELETE CASCADE,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

-- Insertar datos en la tabla Persona
INSERT INTO Persona (dni, nombreCompleto, telefono, correo, direccion) VALUES
('12345678A', 'Juan Pérez', '987654321', 'juan.perez@example.com', 'Calle Falsa 123'),
('23456789B', 'María Gómez', '987654322', 'maria.gomez@example.com', 'Calle Verdadera 456'),
('34567890C', 'Luis Martínez', '987654323', 'luis.martinez@example.com', 'Calle Imaginaria 789');

-- Insertar datos en la tabla Empleado
INSERT INTO Empleado (idPersona, estado, nombreUsuario, contrasenha, salario, fechaContratacion) VALUES
(1, 'ACTIVO', 'juanp', 'password123', 3000.00, '2022-01-15'),
(2, 'INACTIVO', 'mariag', 'password456', 2500.00, '2021-05-20'),
(3, 'ACTIVO', 'luism', 'password789', 3500.00, '2023-03-10');

-- Insertar datos en la tabla Almacen
INSERT INTO Almacen (nombre, estado, descripcion) VALUES
('Almacen Central', 'Activo', 'Almacen principal de la empresa'),
('Almacen Secundario', 'Activo', 'Almacen secundario para productos especiales');

-- Insertar datos en la tabla Administrador
INSERT INTO Administrador (idEmpleado, idAlmacen) VALUES
(1, 1),
(3, 2);

-- Insertar datos en la tabla TrabajadorDeAlmacen
INSERT INTO TrabajadorDeAlmacen (idEmpleado, licenciaMontacarga, idAlmacen) VALUES
(1, TRUE, 1),
(3, FALSE, 2);

-- Insertar datos en la tabla Vendedor
INSERT INTO Vendedor (idEmpleado, ingresosVentas, porcentajeComision) VALUES
(1, 10000.00, 0.05),
(3, 15000.00, 0.07);

-- Insertar datos en la tabla Empresa
INSERT INTO Empresa (nombre, direccion, telefono, email, tipoIndustria) VALUES
('Empresa 1', 'Calle Principal 123', '123456789', 'empresa1@example.com', 'Servicios'),
('Empresa 2', 'Calle Secundaria 456', '987654321', 'empresa2@example.com', 'Comercio');

-- Insertar datos en la tabla Representante
INSERT INTO Representante (idPersona, idEmpresa) VALUES
(1, 1),
(2, 2);

-- Insertar datos en la tabla Proveedor
INSERT INTO Proveedor (idEmpresa, fecha_afiliacion, RUC, razonSocial, calificacion, pais) VALUES
(1, '2022-01-01', '12345678912', 'Proveedor 1', 4.5, 'Perú'),
(2, '2023-01-01', '98765432112', 'Proveedor 2', 4.0, 'Colombia');

-- Insertar datos en la tabla Cliente
INSERT INTO Cliente (idEmpresa, fechaRegistro, fechaUltimaCompra) VALUES
(1, '2022-02-01', '2024-10-01'),
(2, '2023-02-01', '2024-10-02');

-- Insertar datos en la tabla Visita
INSERT INTO Visita (idVendedor, fecha, duracion, idCliente) VALUES
(1, '2024-10-01', '01:30:00', 1),
(2, '2024-10-15', '02:00:00', 2);

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
(2, 'PAGADO', 'COMPRA', NULL, 2, 2);

-- Insertar datos en la tabla LineaDocumento
INSERT INTO LineaDocumento (idDocumento, idProducto, cantidad, precioUnitario) VALUES
(1, 1, 2, 50.00),
(1, 2, 1, 30.00),
(2, 1, 3, 50.00);
