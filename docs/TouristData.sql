INSERT INTO tags (name) VALUES
('Nature'),
('Adventure'),
('Paid'),
('Hiking'),
('Vulcano'),
('Diving');

INSERT INTO attraction (name, description, website) VALUES
('YellowStone', 'National Park', 'https://www.nps.gov/yell/index.htm'),
('Shenandoah', 'National Park', 'https://www.nps.gov/shen/index.htm'),
('Apo Island', 'Diving Site', 'https://www.tripadvisor.dk/Tourism-g1074098-Apo_Island_Dauin_Negros_Oriental_Negros_Island_Visayas-Vacations.html');


INSERT INTO attraction_by_tags (tagId, attractionId) VALUES
-- YellowStone (Nature, Adventure, Hiking, Vulcano)
(1, 1),  -- Nature
(2, 1),  -- Adventure
(4, 1),  -- Hiking
(5, 1),  -- Vulcano

-- Shenandoah (Nature, Adventure, Hiking)
(1, 2),  -- Nature
(2, 2),  -- Adventure
(4, 2),  -- Hiking

-- Apo Island (Diving, Nature, Adventure)
(6, 3),  -- Diving
(1, 3),  -- Nature
(2, 3);  -- Adventure



