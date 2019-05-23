DESCRIBE CUOTAS;
DESCRIBE USUARIOS;
DESCRIBE SOCIOS;
SELECT * FROM SOCIOS;

INSERT INTO CUOTAS VALUES(15, '31/12/2020');
INSERT INTO CUOTAS VALUES(15, '31/12/2021');

INSERT INTO SOCIOS VALUES('LF458','Luc�a','Fern�ndez Solano','28/05/1994','71122252C',648703266,'luciafernandz@gmail.com','USUARIO',SYSDATE,NULL,15,'31/12/2020',NULL,'NO');
INSERT INTO SOCIOS VALUES('EB420','Elena','Zamora Blanco','28/05/1994','71122252C',648703266,'elenazamora@gmail.com','USUARIO',SYSDATE,NULL,15,'31/12/2020',NULL,'NO');

INSERT INTO USUARIOS VALUES('dani','dani','1111a');







--------------------------------------------------------------------------------

-- PROYECTO FINAL, CONSULTORIA E-SPORTS GRUPO MONTA�A ARABA
--Usuarios espec�ficos para el proyecto: eqdam07 / eqdam07

--Notas:
------- Definimos las FK como ON DELETE SET NULL puesto que en el enunciado no se nos indica que se busque una continua depuraci�n
---- de datos, donde solo se mantengan los estrictamente necesarios. Por lo que optamos por almacenar todos los datos posibles

SELECT * FROM USER_CONSTRAINTS ORDER BY CONSTRAINT_NAME DESC;
SELECT * FROM ALL_CONS_COLUMNS;
DESCRIBE CUOTAS;
DESCRIBE SOCIOS;
SET SERVEROUTPUT ON;
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--Creaci�n de tablas
CREATE TABLE CUOTAS(
importe NUMBER(5,2),
anyoValidez DATE,
CONSTRAINT CUO_MULTI_PK PRIMARY KEY(importe, anyoValidez)
);


CREATE TABLE SOCIOS(
codigo VARCHAR2(5) CONSTRAINT SOC_COD_PK PRIMARY KEY,
nombre VARCHAR2(15) CONSTRAINT SOC_NOM_NN NOT NULL,
apellidos VARCHAR2(30) CONSTRAINT SOC_APE_NN NOT NULL,
fechaNac DATE CONSTRAINT SOC_FEC_NN NOT NULL,
dni VARCHAR2(9),
telefono NUMBER(9) CONSTRAINT SOC_TEL_NN NOT NULL,
email VARCHAR2(25) CONSTRAINT SOC_EMAIL_NN NOT NULL,
perfil VARCHAR2(15) CONSTRAINT SOC_PER_NN NOT NULL,
fechaAlta DATE CONSTRAINT SOC_FECALTA_NN NOT NULL,
fechaBaja DATE,
--haPagado BOOLEAN, --�0 = false? tipo de dato Boolean no existe
importeCuota NUMBER(5,2) CONSTRAINT SOC_IMPOR_NN NOT NULL,
anyoValidezCuota DATE CONSTRAINT SOC_ANYOVALIDEZ_NN NOT NULL,
socioResponsable VARCHAR2(5),
CONSTRAINT SOC_TIPOPERFIL_CK CHECK(perfil IN('USUARIO','ADMINISTRADOR')),
CONSTRAINT SOC_IMPORANYO_FK FOREIGN KEY(importeCuota, anyoValidezCuota) REFERENCES CUOTAS(importe, anyoValidez) ON DELETE SET NULL,
CONSTRAINT SOC_SOCIORESPONSABLE_FK FOREIGN KEY(socioResponsable) REFERENCES SOCIOS(codigo) ON DELETE SET NULL
-- hemos contemplado que el dni y el email fuesen �nicos pero consideramos que si un socio se da de baja puede volver a apuntarse y recibir� un nuevo c�digo, por lo que sus datos base (dni, nombre, apellidos, email...) se duplicar�n
-- CONSTRAINT SOC_EMAIL_UQ UNIQUE(email)
-- CONSTRAINT SOC_DNI_UQ UNIQUE(dni)
);
-- finalmente a�adimos haPagado como un VARCHAR donde s�lo aceptar� (SI/NO)
ALTER TABLE SOCIOS ADD haPagado VARCHAR2(2) DEFAULT 'NO';
ALTER TABLE SOCIOS ADD CONSTRAINT SOC_PAGO_CK CHECK (haPagado IN ('SI', 'NO'));
ALTER TABLE SOCIOS MODIFY email VARCHAR2(50);

CREATE TABLE JUNTAS(
fechaInicio DATE CONSTRAINT JUN_MULTI_PK PRIMARY KEY,
fechaFin DATE CONSTRAINT JUN_FECFIN_NN NOT NULL
);

CREATE TABLE CARGO(
tipo VARCHAR2(15),
fechaInicio DATE,
fechaFin DATE,
codSoc VARCHAR2(5),
fechaInicioJunta DATE,
CONSTRAINT CAR_MULTI_PK PRIMARY KEY(tipo, fechaInicio, fechaFin),
CONSTRAINT CAR_TIPO_CK CHECK(tipo IN('PRESIDENTE','VICEPRESIDENTE','SECRETARIO','TESORERO','VOCAL')),
CONSTRAINT CAR_COD_FK FOREIGN KEY(codSoc) REFERENCES SOCIOS(codigo) ON DELETE SET NULL,
CONSTRAINT CAR_FECINI_FK FOREIGN KEY(fechaInicioJunta) REFERENCES JUNTAS(fechaInicio) ON DELETE SET NULL
);

CREATE TABLE ACTIVIDADES(
codigo VARCHAR2(5) CONSTRAINT ACT_COD_PK PRIMARY KEY,
tipo VARCHAR2(25) CONSTRAINT ACT_TIPO_NN NOT NULL,
fecha DATE CONSTRAINT ACT_FEC_NN NOT NULL,
descripcion VARCHAR2(50) CONSTRAINT ACT_DESC_NN NOT NULL,
dificultad VARCHAR2(6) CONSTRAINT ACT_DIF_NN NOT NULL,
precio NUMBER(3,2) CONSTRAINT ACT_PREC_NN NOT NULL,
motivoSuspension VARCHAR2(100),
organizador VARCHAR2(5),
CONSTRAINT ACT_ORGANIZADOR_FK FOREIGN KEY(organizador) REFERENCES SOCIOS(codigo) ON DELETE SET NULL,
CONSTRAINT ACT_TIPO_CK CHECK(tipo IN('SALIDA AL MONTE', 'ALBERGUE FIN DE SEMANA', 'REUNI�N', 'COMIDA', 'OTROS')),
CONSTRAINT ACT_TIPODIFICULTAD_CK CHECK(dificultad IN('ALTA','BAJA','MEDIA'))
);
--motivoSuspensi�n, si su valor es null representa que no ha sido suspendida
--en Java, utilizaremos la clase ActividadSuspendida para rellenar ese dato

--llamamos PARTICIPACIONES a las actividades realizadas por los socios
CREATE TABLE PARTICIPACIONES(
socio VARCHAR2(5),
actividad VARCHAR2(5),
CONSTRAINT PAR_MULTI_PK PRIMARY KEY(socio, actividad),
CONSTRAINT PAR_SOC_FK FOREIGN KEY(socio) REFERENCES SOCIOS(codigo) ON DELETE CASCADE,
CONSTRAINT PAR_ACT_FK FOREIGN KEY(actividad) REFERENCES ACTIVIDADES(codigo) ON DELETE CASCADE
);

CREATE TABLE FECHASDISPONIBLES(
fecha DATE CONSTRAINT FECDIS_FEC_PK PRIMARY KEY,
socio VARCHAR2(5),
CONSTRAINT FECDIS_SOC_FK FOREIGN KEY(socio) REFERENCES SOCIOS(codigo)
);

CREATE TABLE USUARIOS(
nombre VARCHAR2(16) CONSTRAINT USU_NOM_PK PRIMARY KEY,
contrasenya VARCHAR2(16),
socio VARCHAR2(5),
CONSTRAINT USU_SOC_FK FOREIGN KEY(socio) REFERENCES SOCIOS(codigo)
);

CREATE TABLE REGISTRO(
nombre VARCHAR2(16),
fecha TIMESTAMP
);

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--Inserciones de datos normales
INSERT INTO CUOTAS VALUES(25, '31/12/2019');
INSERT INTO SOCIOS VALUES('1111a','Daniel','Tamargo Saiz','23/09/1995','728318A',648703216,'danieltamargosaiz@gmail.com','USUARIO',SYSDATE,NULL,25,'31/12/2019',NULL,DEFAULT);
INSERT INTO SOCIOS VALUES('1111b','Daniela','Tamarga Saiz','23/09/2005','728318A',648703216,'danieltamargosaiz@gmail.com','USUARIO',SYSDATE,NULL,25,'31/12/2019','1111a',DEFAULT);

SET SERVEROUTPUT ON;
SHOW ERRORS;
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--TRIGGERS
--Trigger que controla que el socio es mayor de 4 a�os
CREATE OR REPLACE TRIGGER SOCIOMAYOR4ANYOS 
BEFORE INSERT ON SOCIOS
FOR EACH ROW
DECLARE
    v_edad NUMBER(4);
BEGIN
SELECT(ROUND(MONTHS_BETWEEN(SYSDATE, :new.FECHANAC) / 12)) INTO v_edad FROM DUAL;
    IF v_edad < 4 THEN
        RAISE_APPLICATION_ERROR(-20001,'No pueden registrarse socios menores de 4 a�os de edad.');
    END IF;
END SOCIOMAYOR4ANYOS;

--Trigger que controla que el socio menor de edad tiene un responsable
CREATE OR REPLACE TRIGGER CONTROLRESPONSABLE 
BEFORE INSERT ON SOCIOS
FOR EACH ROW
DECLARE
    v_edad NUMBER(4);
    v_anyoActual NUMBER(4);
    v_anyoFechaNac NUMBER(4);
    v_fechaNac SOCIOS.fechaNac%TYPE;
BEGIN
    --SELECT(ROUND(MONTHS_BETWEEN(SYSDATE, :new.FECHANAC) / 12)) INTO v_edad FROM DUAL;
    --SELECT EXTRACT(YEAR FROM SYSDATE) INTO v_anyoActual;
    v_anyoActual:=EXTRACT(YEAR FROM SYSDATE);
    v_anyoFechaNac:=EXTRACT(YEAR FROM :new.FECHANAC);
    v_edad:=v_anyoActual - v_anyoFechaNac;
    IF v_edad < 18 THEN
        IF :new.SOCIORESPONSABLE IS NULL THEN
            RAISE_APPLICATION_ERROR(-20002,'Los socios menores de edad tienen que tener un socio responsable.');
        ELSE
            SELECT FECHANAC INTO v_fechaNac FROM SOCIOS WHERE CODIGO=:new.SOCIORESPONSABLE;
            --SELECT(ROUND(MONTHS_BETWEEN(SYSDATE, (SELECT FECHANAC FROM SOCIOS WHERE CODIGO=:new.SOCIORESPONSABLE)) / 12)) INTO v_edad FROM DUAL;
            v_anyoFechaNac:=EXTRACT(YEAR FROM v_fechaNac);
            v_edad:=v_anyoActual - v_anyoFechaNac;
            IF v_edad < 18 THEN
                RAISE_APPLICATION_ERROR(-20003,'Los socios menores de edad no pueden ser socios responsables.');
            END IF;
        END IF;
    END IF;
END CONTROLRESPONSABLE;
-- Prueba
INSERT INTO SOCIOS(CODIGO, NOMBRE, APELLIDOS, FECHANAC, DNI, TELEFONO, EMAIL, PERFIL, FECHAALTA, IMPORTECUOTA, ANYOVALIDEZCUOTA, SOCIORESPONSABLE, HAPAGADO) 
VALUES('9999X','Ejemplo','Ejem Plo','31/12/2010','99999999X',
999999999,'ejemplo@ejemplo.plo','USUARIO','14/05/2010',25,'31/12/2019','1111b','NO');

--Trigger que controla que las fechas creadas para las futuras actividades sean fechas posteriores a la fecha actual (quiz�s mejor en Java)


--Trigger que guarda autom�ticamente la informaci�n del Log (no tenemos privilegios suficientes)
CREATE OR REPLACE TRIGGER REGISTROUSUARIO
AFTER LOGON
ON DATABASE
BEGIN
INSERT INTO REGISTRO VALUES(USER,SYStimestamp);
END REGISTROUSUARIO;

--Trigger que actualiza autom�ticamente el perfil USUARIO a ADMINISTRADOR
CREATE OR REPLACE TRIGGER CONTROLPERFIL 
AFTER INSERT ON CARGO
FOR EACH ROW
DECLARE
BEGIN
    UPDATE SOCIOS
    SET PERFIL='ADMINISTRADOR'
    WHERE CODIGO=:new.CODSOC;
    IF SQL%NOTFOUND THEN
        RAISE_APPLICATION_ERROR(-20010,'Error al alterar el perfil del socio ' || :new.CODSOC || '.');
    END IF;
END CONTROLPERFIL;


-- -20001 = Socios menores de 4 a�os
-- -20002 = Socios menores de 18 a�os sin socio responsable
-- -20003 = Los socios menores de edad no pueden ser socios responsables.
-- -20020 = Error al cargar los datos. (NO DATA FOUND)
-- -20025 = Error desconocido. (WHEN OTHERS)

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--Procedimientos y funciones (que ir�n envueltos en un paquete)
CREATE OR REPLACE FUNCTION CUOTA_INFANTIL
    (p_cuota IN CUOTAS.importe%type)
RETURN NUMBER
IS
    v_cuota CUOTAS.importe%type;
BEGIN
    v_cuota:=p_cuota * 0.8;
    RETURN v_cuota;
END CUOTA_INFANTIL;
DROP FUNCTION CUOTA_INFANTIL; --Lo eliminamos para introducirlo en un paquete
-----------------
----PROCEDIMIENTO QUE D� DE BAJA A LOS QUE NO HAYAN PAGADO SU CUOTA AL TERMINAR EL A�O
CREATE OR REPLACE PROCEDURE DARDEBAJA
    (p_codigo IN SOCIOS.codigo%TYPE)
IS
    v_num NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_num FROM SOCIOS;
    
    UPDATE SOCIOS
    SET FECHABAJA=SYSDATE
    WHERE CODIGO=p_codigo;
    
    FOR i IN 1..v_num LOOP
        UPDATE SOCIOS
        SET FECHABAJA=SYSDATE
        WHERE SOCIORESPONSABLE=p_codigo;
    END LOOP;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20020,'Error al cargar los datos.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20025,'Error desconocido.');
END DARDEBAJA;
DROP PROCEDURE DARDEBAJA; --Lo eliminamos para introducirlo en un paquete
---------------
----PROCEDIMIENTO QUE RESETEE EL VALOR DE HAPAGADO DE CADA SOCIO AL COMENZAR EL A�O
CREATE OR REPLACE PROCEDURE RESETEODEPAGOS
IS
BEGIN
    UPDATE SOCIOS
    SET HAPAGADO='NO';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20020,'Error al cargar los datos.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20020,'Error desconocido.');
END RESETEODEPAGOS;
DROP PROCEDURE RESETEODEPAGOS; --Lo eliminamos para introducirlo en un paquete

--Paquete con los procedimientos: RESETEODEPAGOS y DARDEBAJA y la funci�n CUOTAINFANTIL
CREATE OR REPLACE PACKAGE PAQUETE1 IS
    PROCEDURE RESETEODEPAGOS;
    PROCEDURE DARDEBAJA (p_codigo IN SOCIOS.codigo%TYPE);
    FUNCTION CUOTAINFANTIL (p_cuota IN CUOTAS.importe%type) RETURN NUMBER;
END;

CREATE OR REPLACE PACKAGE BODY PAQUETE1 IS
    PROCEDURE RESETEODEPAGOS
        IS
        BEGIN
            UPDATE SOCIOS
            SET HAPAGADO='NO';
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                RAISE_APPLICATION_ERROR(-20020,'Error al cargar los datos.');
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20020,'Error desconocido.');
        END RESETEODEPAGOS;
    PROCEDURE DARDEBAJA (p_codigo IN SOCIOS.codigo%TYPE)
        IS
            v_num NUMBER;
        BEGIN
            SELECT COUNT(*) INTO v_num FROM SOCIOS;
            
            UPDATE SOCIOS
            SET FECHABAJA=SYSDATE
            WHERE CODIGO=p_codigo;
            
            FOR i IN 1..v_num LOOP
                UPDATE SOCIOS
                SET FECHABAJA=SYSDATE
                WHERE SOCIORESPONSABLE=p_codigo;
            END LOOP;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                RAISE_APPLICATION_ERROR(-20020,'Error al cargar los datos.');
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20025,'Error desconocido.');
        END DARDEBAJA;
    FUNCTION CUOTAINFANTIL (p_cuota IN CUOTAS.importe%type)
        RETURN NUMBER
        IS
            v_cuota CUOTAS.importe%type;
        BEGIN
            v_cuota:=p_cuota * 0.8;
            RETURN v_cuota;
        END CUOTAINFANTIL;
END;
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--Vistas
CREATE OR REPLACE VIEW CALENDARIO AS   
    (SELECT A.FECHA, A.TIPO, A.DESCRIPCION, A.PRECIO, S.NOMBRE, S.APELLIDOS, A.MOTIVOSUSPENSION 
    FROM ACTIVIDADES A, SOCIOS S)
WITH READ ONLY CONSTRAINT CAL_DML_RO;



--Dudas:
-- 1- C�mo programar que un procedimiento � funci�n se ejecute en una determinada fecha
-- 2- Errores con el Trigger, comprobar



--A tener en cuenta:
-- 1- No hemos creado indexes m�s all� de los que crea el propio programa con las PK
------ (no los consider�bamos necesarios)
-- 2- No hemos utilizado el comando para dar permisos a otros usuarios en la BBDD
------ Crear usuario: CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
------ Dar privilegios: GRANT ALL PRIVILEGES ON database.table TO 'user'@'localhost';
------ Motivo: la conexi�n la haremos con este pc y esta base de datos, a trav�s de Java
---------- y de la variable "perfil" cuyo valor es "usuario" � "administrador" modificaremos
---------- el programa