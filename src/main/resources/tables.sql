CREATE TABLE IF NOT EXISTS clientes(
    id TEXT PRIMARY KEY NOT NULL,
    nombre TEXT NOT NULL,
    is_deleted BOOLEAN DEFAULT false
);