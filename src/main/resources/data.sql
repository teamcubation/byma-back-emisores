-- Tabla: ACDI
INSERT INTO ACDI (ID_ORGANIZACION_ACDI, DENOMINACION, LIQUIDA_EN_BYMA, HABILITADO, BILLETERAS, OBSERVACIONES, FECHA_ALTA, MAIL, ESTADO)
VALUES
    ('ORG001', 'Acdi Principal', true, true, false, 'Primera carga de prueba', '2023-01-01 12:00:00', 'acdi@example.com', 0),
    ('ORG002', 'Acdi Secundaria', false, true, true, 'Segunda carga de prueba', '2023-01-02 12:00:00', 'acdi2@example.com', 0),
    ('ORG003', 'Acdi Tercera', true, false, false, 'Tercera carga de prueba', '2023-01-03 12:00:00', 'acdi3@example.com', 0),
    ('ORG004', 'Acdi Cuarta', false, false, true, 'Cuarta carga de prueba', '2023-01-04 12:00:00', 'acdi4@example.com', 0),
    ('ORG005', 'Acdi Quinta', true, true, true, 'Quinta carga de prueba', '2023-01-05 12:00:00', 'acdi5@example.com', 0),
    ('ORG006', 'Acdi Sexta', false, false, false, 'Sexta carga de prueba', '2023-01-06 12:00:00', 'acdi6@example.com', 0),
    ('ORG007', 'Acdi Septima', true, true, false, 'Septima carga de prueba', '2023-01-07 12:00:00', 'acdi7@example.com', 0),
    ('ORG008', 'Acdi Octava', false, true, true, 'Octava carga de prueba', '2023-01-08 12:00:00', 'acdi8@example.com', 0);

-- Tabla: BILLETERA
INSERT INTO BILLETERAS (MAIL, ID_CUENTA, DENOMINACION, LIQUIDA_EN_BYMA, HABILITADO, FECHA_ALTA, OBSERVACIONES, ID_ACDI)
VALUES
    ( 'usuario1@example.com', 101, 'Cuenta A', true, true, '2023-01-01 12:00:00', 'Usuario principal', 1),
    ( 'usuario2@example.com', 102, 'Cuenta B', false, true, '2023-01-02 12:00:00', 'Usuario secundario', 2),
    ('usuario3@example.com', 103, 'Cuenta C', true, false, '2023-01-03 12:00:00', 'Usuario terciario', 3),
    ('usuario4@example.com', 104, 'Cuenta D', false, false, '2023-01-04 12:00:00', 'Usuario cuaternario', 4),
    ('usuario5@example.com', 105, 'Cuenta E', true, true, '2023-01-05 12:00:00', 'Usuario quinto', 5),
    ('usuario6@example.com', 106, 'Cuenta F', false, true, '2023-01-06 12:00:00', 'Usuario sexto', 6),
    ('usuario7@example.com', 107, 'Cuenta G', true, false, '2023-01-07 12:00:00', 'Usuario septimo', 7),
    ('usuario8@example.com', 108, 'Cuenta H', false, false, '2023-01-08 12:00:00', 'Usuario octavo', 8);

-- Tabla: EMISOR
INSERT INTO EMISORES (DENOMINACION, EMAIL, FECHA_ALTA, CUENTA_EMISOR, ID_ORGANIZACION, ID_ENTIDAD_LEGAL)
VALUES
    ( 'Org Principal', 'org1@example.com', '2023-01-01 12:00:00', 'CuentaOrg1', 1001, 2001),
    ( 'Org Secundaria', 'org2@example.com', '2023-01-02 12:00:00', 'CuentaOrg2', 1002, 2002),
    ('Org Terciaria', 'org3@example.com', '2023-01-03 12:00:00', 'CuentaOrg3', 1003, 2003),
    ('Org Cuaternaria', 'org4@example.com', '2023-01-04 12:00:00', 'CuentaOrg4', 1004, 2004),
    ('Org Quinta', 'org5@example.com', '2023-01-05 12:00:00', 'CuentaOrg5', 1005, 2005),
    ('Org Sexta', 'org6@example.com', '2023-01-06 12:00:00', 'CuentaOrg6', 1006, 2006),
    ('Org Septima', 'org7@example.com', '2023-01-07 12:00:00', 'CuentaOrg7', 1007, 2007),
    ('Org Octava', 'org8@example.com', '2023-01-08 12:00:00', 'CuentaOrg8', 1008, 2008);


-- Tabla: ESPECIE
INSERT INTO ESPECIES (CODIGO_CVSA, DENOMINACION, LAMINA_MINIMA, PRECIO, CAFCI, CUENTA_DE_EMISION, ESTADO, ID_EMISOR, ID_GERENTE, VIGENCIA, PLAZO_DE_LIQUIDACION, CODIGO_CNV, ISIN, FAMILIA_DE_FONDOS, OBSERVACIONES, ID_MONEDA, FECHA_ALTA)
VALUES
    ( 'CVSA001', 'Especie A', 10, 100.50, 'CAF001', 'CuentaEsp1', 'ACTIVO', 1, 1, '2024-01-01', '2024-12-31', 'CNV001', 'ISIN001', 'Fondo A', 'Especie principal', 1, '2023-01-01'),
    ( 'CVSA002', 'Especie B', 20, 200.75, 'CAF002', 'CuentaEsp2', 'ACTIVO', 2, 2, '2024-02-01', '2024-11-30', 'CNV002', 'ISIN002', 'Fondo B', 'Especie secundaria', 2, '2023-01-02'),
    ('CVSA003', 'Especie C', 15, 300.25, 'CAF003', 'CuentaEsp3', 'INACTIVO', 3, 3, '2024-03-01', '2024-10-31', 'CNV003', 'ISIN003', 'Fondo C', 'Especie terciaria', 3, '2023-01-03'),
    ('CVSA004', 'Especie D', 25, 400.00, 'CAF004', 'CuentaEsp4', 'ACTIVO', 4, 4, '2024-04-01', '2024-09-30', 'CNV004', 'ISIN004', 'Fondo D', 'Especie cuaternaria', 4, '2023-01-04'),
    ('CVSA005', 'Especie E', 30, 500.50, 'CAF005', 'CuentaEsp5', 'ACTIVO', 5, 5, '2024-05-01', '2024-08-31', 'CNV005', 'ISIN005', 'Fondo E', 'Especie quinta', 5, '2023-01-05'),
    ('CVSA006', 'Especie F', 35, 600.75, 'CAF006', 'CuentaEsp6', 'INACTIVO', 6, 6, '2024-06-01', '2024-07-31', 'CNV006', 'ISIN006', 'Fondo F', 'Especie sexta', 6, '2023-01-06'),
    ('CVSA007', 'Especie G', 40, 700.25, 'CAF007', 'CuentaEsp7', 'ACTIVO', 7, 7, '2024-07-01', '2024-06-30', 'CNV007', 'ISIN007', 'Fondo G', 'Especie septima', 7, '2023-01-07'),
    ('CVSA008', 'Especie H', 45, 800.00, 'CAF008', 'CuentaEsp8', 'INACTIVO', 8, 8, '2024-08-01', '2024-05-31', 'CNV008', 'ISIN008', 'Fondo H', 'Especie octava', 8, '2023-01-08');

-- Tabla: GERENTE
INSERT INTO GERENTES (DENOMINACION, LIQUIDA_EN_BYMA, HABILITADO, OBSERVACIONES, MAIL_GERENTE, FECHA_DE_ALTA)
VALUES
    ( 'Gerente A', true, true, 'Gerente principal', 'gerente1@example.com', '2023-01-01 12:00:00'),
    ( 'Gerente B', false, true, 'Gerente secundario', 'gerente2@example.com', '2023-01-02 12:00:00'),
    ('Gerente C', true, false, 'Gerente terciario', 'gerente3@example.com', '2023-01-03 12:00:00'),
    ('Gerente D', false, false, 'Gerente cuaternario', 'gerente4@example.com', '2023-01-04 12:00:00'),
    ('Gerente E', true, true, 'Gerente quinto', 'gerente5@example.com', '2023-01-05 12:00:00'),
    ('Gerente F', false, true, 'Gerente sexto', 'gerente6@example.com', '2023-01-06 12:00:00'),
    ('Gerente G', true, false, 'Gerente séptimo', 'gerente7@example.com', '2023-01-07 12:00:00'),
    ('Gerente H', false, false, 'Gerente octavo', 'gerente8@example.com', '2023-01-08 12:00:00');


-- Tabla: SUSCRIPCION_MODEL
INSERT INTO SUSCRIPCIONES(
    ESTADO,FECHA_ALTA,NRO_CERTIFICADO,ID_ESPECIE,CANT_CUOTAPARTES,ID_ACDI,ID_EMISOR,NRO_PEDIDO,NRO_SECUENCIA,FECHA_CAMBIO_DE_ESTADO,ROL_INGRESANTE,MONTO,LIQUIDA_EN_BYMA,NUMERO_REFERENCIA,PROCESADO_CUSTODIA,ULTIMO_ERROR,COMMAND,PROCESADO_LIQUIDACIONES_SLYQ,ID_GERENTE,OBLIGACION_DE_PAGO_GENERADA,ID_BILLETERA,FECHA_SINCRONIZACION,NASDAQ_SI_STATUS_REASON,MDW_STATUS_CODE,MDW_BUSINESS_MESSAGE_ID,MDW_RESPONSE_MESSAGE,MDW_RESPONSE_DATETIME,NASDAQ_SI_STATUS
) VALUES
    ('ACTIVO','2024-12-17 14:30:00',123456,789,5,12345,67890,1001,10,'2024-12-17','ADMIN',1000.50,TRUE,1234567,TRUE,'No error','command_123',FALSE,4567,FALSE,1,'2024-12-17 14:35:00','Reason',200,'msg123','response_msg','2024-12-17 14:36:00','Active'),
    ('INACTIVO','2024-11-10 10:15:00',654321,987,3,23456,78901,2002,20,'2024-11-10','USER',500.75,FALSE,2345678,FALSE,'Timeout error','command_456',TRUE,5678,TRUE,2,'2024-11-10 10:20:00','Disconnected',201,'msg456','error_msg','2024-11-10 10:25:00','Inactive'),
    ('PENDIENTE','2024-12-15 12:00:00',345678,654,7,34567,89012,3003,30,'2024-12-15','SUPERVISOR',1200.25,TRUE,3456789,TRUE,'Invalid data','command_789',FALSE,6789,FALSE,3,'2024-12-15 12:05:00','Pending approval',202,'msg789','pending_msg','2024-12-15 12:10:00','Pending'),
    ('ACTIVO','2024-12-16 08:45:00',987654,321,10,45678,12345,4004,40,'2024-12-16','ADMIN',2000.99,TRUE,4567890,FALSE,'No error','command_321',TRUE,7890,TRUE,4,'2024-12-16 08:50:00','Confirmed',203,'msg321','success_msg','2024-12-16 08:55:00','Active'),
    ('INACTIVO','2024-12-18 09:00:00',112233,432,8,56789,90123,5005,50,'2024-12-18','ADMIN',1500.75,FALSE,5678901,FALSE,'Network error','command_555',TRUE,1234,FALSE,5,'2024-12-18 09:05:00','Disconnected',204,'msg555','disconnected_msg','2024-12-18 09:10:00','Inactive'),
    ('ACTIVO','2024-12-19 10:00:00',223344,876,6,67890,12345,6006,60,'2024-12-19','USER',900.50,TRUE,6789012,TRUE,'No error','command_666',FALSE,2345,TRUE,6,'2024-12-19 10:05:00','Connection successful',205,'msg666','connected_msg','2024-12-19 10:10:00','Active'),
    ('PENDIENTE','2024-12-20 11:00:00',334455,765,4,78901,23456,7007,70,'2024-12-20','SUPERVISOR',1200.10,TRUE,7890123,TRUE,'Invalid request','command_777',FALSE,3456,FALSE,7,'2024-12-20 11:05:00','Awaiting validation',206,'msg777','pending_validation_msg','2024-12-20 11:10:00','Pending'),
    ('ACTIVO','2024-12-21 12:00:00',445566,543,9,89012,34567,8008,80,'2024-12-21','USER',2000.99,TRUE,8901234,FALSE,'No error','command_888',TRUE,4567,TRUE,8,'2024-12-21 12:05:00','Confirmed',207,'msg888','success_msg','2024-12-21 12:10:00','Active');
