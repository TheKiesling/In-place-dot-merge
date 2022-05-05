CREATE (casaEscobar:place {name: "Casa Escobar"})
CREATE (irtra:place {name: "Irtra Petapa"})
CREATE (earthlodge:place {name: "Earth Lodge"})
CREATE (pareja:relation {name: "Pareja"})
CREATE (familia:relation {name: "Familia"})

CREATE (casaEscobar)-[:RELATION]->(pareja)
CREATE (earthlodge)-[:RELATION]->(pareja)
CREATE (irtra)-[:RELATION]->(familia)

Match (n) RETURN n