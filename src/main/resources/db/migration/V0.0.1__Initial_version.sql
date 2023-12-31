CREATE TABLE public.TB_USER_EVENT (
          ID_KEY UUID DEFAULT gen_random_uuid() PRIMARY KEY,
          DT_HR_CREATED TIMESTAMP,
          DS_EVENT TEXT NOT NULL,
          ID_USER VARCHAR(36)  NOT NULL,
          DS_OLD_VALUE_JSON TEXT,
          DS_NEW_VALUE_JSON TEXT
);


-- Trigger para geração dos eventos

-- Criação da função que será executada pela trigger
CREATE OR REPLACE FUNCTION public.user_entity_event_trigger_function()
RETURNS TRIGGER AS $$
DECLARE
event_trigger text;
BEGIN
	IF TG_OP = 'INSERT' THEN
        event_trigger := 'INSERTED';
        INSERT INTO public.TB_USER_EVENT (DT_HR_CREATED, DS_EVENT, ID_USER, DS_OLD_VALUE_JSON, DS_NEW_VALUE_JSON)
        VALUES ( NOW(), event_trigger,UUID(NEW.id), null, row_to_json(NEW)::jsonb::text);
    ELSIF TG_OP = 'UPDATE' THEN
        event_trigger := 'UPDATED';
        INSERT INTO public.TB_USER_EVENT (DT_HR_CREATED, DS_EVENT, ID_USER, DS_OLD_VALUE_JSON, DS_NEW_VALUE_JSON)
        VALUES (NOW(), event_trigger,UUID(OLD.id), row_to_json(OLD)::jsonb::text, row_to_json(NEW)::jsonb::text);
    ELSIF TG_OP = 'DELETE' THEN
        event_trigger := 'DELETED';
        INSERT INTO public.TB_USER_EVENT (DT_HR_CREATED, DS_EVENT, ID_USER, DS_OLD_VALUE_JSON, DS_NEW_VALUE_JSON)
        VALUES (NOW(), event_trigger,UUID(OLD.id), row_to_json(OLD)::jsonb::text, null);
    END IF;


RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER user_entity_event_trigger
    AFTER INSERT OR UPDATE OR DELETE
        ON public.user_entity
            FOR EACH ROW
            EXECUTE FUNCTION user_entity_event_trigger_function();

--Controlle Camel process
create table public.CAMEL_MESSAGEPROCESSED (id bigint not null, createdAt timestamp(6), messageId varchar(255), processorName varchar(255), primary key (id));
alter table if exists public.CAMEL_MESSAGEPROCESSED drop constraint if exists UK3gjvyad9j4kd1xeog8xws0skp;
alter table if exists public.CAMEL_MESSAGEPROCESSED add constraint UK3gjvyad9j4kd1xeog8xws0skp unique (processorName, messageId);
create sequence public.CAMEL_MESSAGEPROCESSED_SEQ start with 1 increment by 50;



/*
 CREATE TABLE exemplo (
  id SERIAL PRIMARY KEY,
  campo_texto TEXT,
  campo_integer INTEGER,
  campo_decimal DECIMAL(10,2),
  campo_boolean BOOLEAN,
  campo_data DATE,
  campo_hora TIME,
  campo_datahora TIMESTAMP,
  campo_json JSON,
  campo_array INTEGER[],
  campo_uuid UUID
);

 CREATE TABLE exemplo1 (
  id SERIAL PRIMARY KEY,
  campo_texto TEXT NOT NULL,
  campo_integer INTEGER DEFAULT 0
);

 CREATE TABLE exemplo2 (
  id SERIAL PRIMARY KEY,
  campo_texto TEXT UNIQUE
);
 CREATE TABLE exemplo3 (
  id SERIAL PRIMARY KEY,
  campo_integer INTEGER CHECK (campo_integer >= 0)
);

 CREATE TABLE exemplo4 (
  id SERIAL PRIMARY KEY,
  campo_referencia INTEGER,
  FOREIGN KEY (campo_referencia) REFERENCES outra_tabela(id)
);

 -- Criação da sequência
CREATE SEQUENCE exemplo_seq START 1 INCREMENT 1;

 CREATE TEMPORARY TABLE exemplo_temp (
  id SERIAL PRIMARY KEY,
  campo_texto TEXT
);

 -- Criação de um índice
CREATE INDEX exemplo_index ON exemplo (campo_texto);

 -- Criação de uma visão (view)
CREATE VIEW exemplo_view AS
SELECT id, campo_texto
FROM exemplo
WHERE campo_texto = 'valor';

 -- Criação de uma função
CREATE FUNCTION exemplo_func(parametro INT)
RETURNS INT AS $$
  DECLARE
    resultado INT;
  BEGIN
    resultado := parametro * 2;
    RETURN resultado;
  END;
$$ LANGUAGE plpgsql;
 */