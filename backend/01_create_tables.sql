USE comerzia_db;

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
    company_type ENUM('client_company', 'provider_company'), -- Discriminator column

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
    person_type ENUM('employee_person', 'representative_person'), -- Discriminator column

    -- Fields for Empleado
    estado ENUM('ACTIVO', 'INACTIVO'),
    nombre_usuario VARCHAR(50),
    contrasenha VARCHAR(255),
    salario DOUBLE,
    fecha_contratacion DATE,
    -- Common fields for all employee types
    employee_type ENUM('admin_employee', 'warehouse_employee', 'sales_employee'), -- Discriminator column

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
