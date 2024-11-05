use ComerziaDB;

-- Eliminar tabla LineaDocumento
DROP TABLE IF EXISTS LineaDocumento;

-- Eliminar tabla Documento
DROP TABLE IF EXISTS Documento;

-- Eliminar tabla ProductoAlmacenado
DROP TABLE IF EXISTS ProductoAlmacenado;

-- Eliminar tabla Empresa table (now includes STI for Empresa, Representante, Cliente, and Proveedor)
DROP TABLE IF EXISTS Empresa;

-- Eliminar tabla Producto
DROP TABLE IF EXISTS Producto;

-- Eliminar tabla Persona table (now includes STI for Persona, Empleado, Administrador, Trabajador, and Vendedor)
DROP TABLE IF EXISTS Persona;

-- Eliminar tabla Almacen
DROP TABLE IF EXISTS Almacen;
