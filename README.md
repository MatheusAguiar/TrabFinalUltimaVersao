# TrabFinal

Gatilhos 

CREATE TRIGGER SubtraiExemplar
AFTER
INSERT ON emprestimo
FOR EACH ROW
BEGIN
UPDATE exemplar SET
numExemplar = numExemplar-1
WHERE id = NEW.exemplar_id;
END

CREATE TRIGGER SomaExemplar
AFTER
UPDATE ON emprestimo
FOR EACH ROW
BEGIN
UPDATE exemplar SET
numExemplar = numExemplar+1
WHERE id = NEW.exemplar_id; 
END
