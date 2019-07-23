# n64SaveConverter
Converts n64 save files to all available types of n64 save formats.

Setup/Compatibility
__________________________________

1. This app is only compatible with Android SDK 26 and above.
2. This app can be cloned and built from any installation of Android studio.

N64 Save Conversion
----------------------------------

1. On original N64 hardware, there are 5 N64 save formats with the following sizes (eep 4kb, eep 16kb, sra 256kb, fla 1mb, mpk 256kb per memory pak).

2. Emulators in general may not follow the exact size guidelines above. For each save format, they may expect larger save file sizes than mentioned above (In which case they pad the file with empty bytes at the beginning or end of the file).

3. General Rule: Emulators can read save files which are larger (more padding at the end of the file) than the actual save file size that the emulator produces, but not smaller. Therefore, to be safe, it is encouraged to pad as much as you want at the end of your save file to increase compatablility across emulators. EXCEPT when creating Virtual Console saves. They expect exact sizes. Luckily, packed VC saves expect sizes which are equal to or larger than all other emulators. 

4. PC emulators are little endian, Wii emulators (Wii64 and VC) are big endian. But this only matters for fla and sra saves, not eep and mpk. So they require (32 bit) byte swapping to be compatible.

5. My N64SaveConverter.exe can be run in 2 ways. By dragging and dropping a single file onto the executable or by running the executable alone which will search for all n64 save file in the current directory and process all of them.

6. My save converter will do the following things: Pad all save formats  to the Wii VC size format (the largest of all emulators). For sra and fla, it will also do a (32 bit) byte swap. And it will also create a separate retroarch formatted save (srm). If you run the exe with an srm save, it will create 4 save files (eep, sra, fla, mpk) without any byte swapping. Simply select the correct format and discard the others.

7. For sra and fla, you cannot go from a retroarch save directly to a wii save and vice versa. You would need to run it with the srm and then run it again with the output file to do the byte swapping. For the other way, you would need to run it with the sra/fla to do the byteswapping (ignore the srm) and then run it again with the output to get the correct srm.

N64 Wii VC Title IDs
---------------------------------

Super Mario 64 - NAAE
Mario Kart 64 - NABE
Ocarina of Time - NACE
Star Fox 64 - NADE
Paper Mario - NAEE
F Zero X - NAFE
Yoshi Story - NAHE
Wave Race 64 - NAIE
Sin and Punishment - NAJN
Pokemon Snap - NAKE
Super Smash Bros - NALE
Kirby 64 - NAME
Pokemon Puzzle League - NANE
1080 Snowboarding - NAOE
Majoras Mask - NARE 
Cruisn USA - NASE
Mario Tennis - NATE
Mario Golf - NAUE
Ogre Battle 64 - NAYE
Mario Party 2 - NAZE
Bomberman Hero - NA3E



N64 Save Formats
---------------------------------

Save Method Overview
These are the 5 known saving methods:

Controller Pak (32,768 bytes) SRAM
4 Kilobits (512 bytes) EEPROM
16 Kilobits (2048 bytes) EEPROM
256 Kilobits (32,768 bytes) SRAM
1024 Kilobits (131,072 bytes) FlashRAM
No Save (included at the bottom)
The bottom four methods are for on-cartridge saving. They function basically the same to the end user. SRAM (Static RAM) uses a coin cell battery as it is a volatile form of memory. EEPROM and Flash RAM do not require an external power source to retain data.

There also exists one unique N64 game, Dezaemon 3D. It uses 768Kbit SRAM, thus also uses a battery.

NOTE: Sometimes a game uses both on-cart memory and the controller pak. Such games will be placed in each of the relevant lists.

--------------------------------------
Controller Pak .mpk
Also known as a memory card. Most 3rd party games used this to save. Remember, N64 memory cards each use a battery. Within about 20 to 25 years from manufacture, their batteries will have to be replaced.


Aero Gauge
Aidyn Chronicles: The First Mage
All-Star Baseball 2000
All-Star Baseball 2001
All-Star Baseball '99
Armorines: Project S.W.A.R.M.
Army Men: Air Combat
Army Men: Sarge's Heroes
Army Men: Sarge's Heroes 2
Asteroids Hyper 64
Automobili Lamborghini
Bassmaster 2000
Battle Tanx
Battle Tanx: Global Assault
Battlezone: Rise of the Black Dogs
Beetle Adventure Racing
Big Mountain 2000
Bio F.R.E.A.K.S.
Blast Corps
Blues Brothers 2000
Bomberman 64
Bottom of the 9th
Brunswick Circuit Pro Bowling
Buck Bumble
Bug's Life, A
Bust-A-Move 2: Arcade Edition
Bust-A-Move '99
California Speed
Carmageddon 64
Castlevania
Castlevania: Legacy of Darkness
Centre Court Tennis (PAL)
Chameleon Twist 2
Cruis'n USA
CyberTiger
Daikatana
Deadly Arts
Destruction Derby
Diddy Kong Racing
Doom 64
Dual Heroes
Duke Nukem 64
Duke Nukem: Zero Hour
ECW: Hardcore Revolution
Excitebike 64
Extreme-G
Extreme-G 2
F1 Pole Position 64
F1 Racing Championship (PAL)
FIFA '99
FIFA Soccer 64
FIFA: Road to World Cup 98
Fighters Destiny
Fighter Destiny 2
Fighting Force 64
Flying Dragon
Forsaken 64
Fox Sports College Hoops '99
Gauntlet Legends
Gex 3: Deep Cover Gecko
Gex 64: Enter the Gecko
Goemon's Great Adventure
Golden Nugget 64
GT64 Championship Edition
Hercules: The Legendary Journeys
Hexen
Hot Wheels Turbo Racing
Hybrid Heaven
Hydro Thunder
Iggy's Reckin' Balls
International Superstar Soccer 64
International Superstar Soccer '98
International Superstar Soccer 2000
International Track & Field 2000
Jeremy McGrath Supercross 2000
Killer Instinct Gold
Knockout Kings 2000
Kobe Bryant in NBA Courtside
Lego Racers
Madden 2000
Madden 2001
Madden 2002
Madden 64
Madden '99
Mario Kart 64
Mia Hamm Soccer 64
Micro Machines 64 Turbo
Midway's Greatest Arcade Hits Volume 1
Mike Piazza's Strike Zone
Milo's Astro Lanes
Monaco Grand Prix
Mortal Kombat 4
Mortal Kombat Mythologies: Sub-Zero
Ms. Pac-Man Maze Madness
Multi-Racing Championship
Mystical Ninja Starring Goemon
Nagano Winter Olympics '98
Namco Museum 64
Nascar 2000
Nascar '99
NBA Hang Time
NBA In the Zone 2000
NBA In the Zone '98
NBA In the Zone '99
NBA Jam 2000
NBA Jam '99
NBA Live 2000
NBA Live '99
NBA Show Time: NBA on NBC
New Tetris, The
NFL Blitz
NFL Blitz 2000
NFL Blitz 2001
NFL Blitz Special Edition
NFL Quarterback Club 2000
NFL Quarterback Club 2001
NFL Quarterback Club '98
NFL Quarterback Club '99
NHL '99
NHL Blades of Steel '99
NHL Breakaway Hockey '98
NHL Breakaway Hockey '99
Nightmare Creatures
Nuclear Strike 64
Off Road Challenge
Olympic Hockey '98
Paperboy
Penny Racers
Perfect Dark
Polaris Snocross
Power Rangers: Lightspeed Rescue
Premier Manager 64 (PAL)
Quake
Quake II
Quest 64
Rainbow Six
Rakuga Kids (PAL)
Rally Challenge 2000
Rampage 2: Universal Tour
Rampage: World Tour
Rat Attack
Rayman 2: The Great Escape
Razor Freestyle Scooter
Ready 2 Rumble Boxing
Ready 2 Rumble Boxing Round 2
Re-Volt
Road Rash 64
Roadsters
Robotron 64
Rugrats in Paris: The Movie
Rush 2: Extreme Racing USA
S.C.A.R.S.
San Francisco Rush: 2049
San Francisco Rush: Extreme Racing
Scooby-Doo: Classic Creep Capers
Shadow Man
Shadowgate 64: Trials of the Four Towers
Snowboard Kids
South Park
South Park Rally
Space Invaders
Spider-Man
Stunt Racer 64
Supercross 2000
Superman
Tarzan
The World Is Not Enough
Tonic Trouble
Tony Hawk's Pro Skater
Tony Hawk's Pro Skater 2
Tony Hawk's Pro Skater 3
Top Gear Hyper Bike
Top Gear Rally
Top Gear Rally 2
Toy Story 2
Triple Play 2000
Turok: Dinosaur Hunter
Turok 2: Seeds of Evil
Turok 3: Shadow of Oblivion
Turok: Rage Wars
Twisted Edge Extreme Snowboarding
Vigilante 8
Vigilante 8: 2nd Offense
Virtual Chess 64
Virtual Pool 64
Wave Race 64
Wayne Gretzky's 3D Hockey
Wayne Gretzky's 3D Hockey '98
WCW Backstage Assault
WCW Mayhem
WCW Nitro
WCW vs. NWO: World Tour
Wetrix
WinBack: Covert Operations
Wipeout 64
World Cup '98
World Driver Championship
Wrestlemania 2000
WWF: Attitude
WWF: Warzone
Xena: Talisman of Fate

-------------------------------------
4Kbit EEPROM .eep
The second most common form of saving. Most carts with internal saving used this.


AeroFighters Assault
Air Boarder 64 (PAL)
All-Star Tennis '99
Banjo-Kazooie
Bass Hunter 64
Big Mountain 2000
Blast Corps
Body Harvest
Bomberman 64
Bomberman 64: Second Attack
Bomberman Hero
Chameleon Twist
Chopper Attack
Cruis'n Exotica
Cruis'n USA
Diddy Kong Racing
Donald Duck: Goin' Quackers
Dr. Mario 64
Earthworm Jim 3D
F-1 World Grand Prix
F-1 World Grand Prix II (PAL)
Fighters Destiny
Fighter Destiny 2
Glover
GoldenEye 007
GT64 Championship Edition
Hey you, Pikachu!
Indiana Jones and the Infernal Machine
Indy Racing 2000
Killer Instinct Gold
Kirby 64: The Crystal Shards
Lode Runner 3D
Loony Toons: Duck Dodgers
Mario Kart 64
Mario Party
Mario Party 2
Mickey's Speedway USA
Mischief Makers
Mission: Impossible
Monopoly
Multi-Racing Championship
Penny Racers
PGA European Tour
Pilotwings 64
Premier Manager 64 (PAL)
Rocket: Robot on Wheels
Snowboard Kids 2
Space Station Silicon Valley
Star Fox 64
Star Soldier: Vanishing Earth
Star Wars Episode 1: Battle for Naboo
Star Wars: Rogue Squadron
Star Wars: Shadows of the Empire
Starshot Space Circus
Super Mario 64
Taz Express (PAL)
Tetrisphere
Tom & Jerry in Fists of Furry
Top Gear Overdrive
V-Rally Edition '99
Waialae Country Club: True Golf Classics
Wave Race 64
Worms Armageddon


------------------------------------
16Kbit EEPROM .eep
As you can see, not many carts used this to save.

Banjo-Tooie
Conker's Bad Fur Day
Cruis'n World
Donkey Kong 64
Excitebike 64
Kobe Bryant in NBA Courtside
Mario Party 3
Mario Tennis
Perfect Dark
Ridge Racer 64
Star Wars Episode 1 Racer
Yoshi's Story


------------------------------------
256Kbit SRAM .sra
These carts have a battery to power the SRAM when not being played. Like the Controller Pak, these games will eventually need to have their batteries replaced.

1080 Snowboarding
Dezaemon 3D (JPN, 768Kbit)
F-Zero X
Harvest Moon 64
Legend of Zelda: Ocarina of Time, The
Major League Baseball featuring Ken Griffey Jr.
Mario Golf
New Tetris, The
Ogre Battle 64: Person of Lordly Caliber
Resident Evil 2
Super Smash Bros.
WCW/NWO Revenge
WWF: Wrestlemania 2000
Animal Crossing (JPN) is an odd case. Although this game uses Flash RAM, it needs a battery for the real time clock.


--------------------------------
1Mbit Flash RAM .fla
A few games used this rather fancy (and costly) save method.

Command & Conquer
Jet Force Gemini
Ken Griffey Jr's Slugfest
Legend of Zelda: Majora's Mask, The
Megaman 64
NBA Courtside 2 featuring Kobe Bryant
Paper Mario
Pokemon Puzzle League
Pokemon Snap
Pokemon Stadium
Pokemon Stadium 2
Starcraft 64
Tigger's Honey Hunt
WWF: No Mercy


-----------------------
No Save
Surprisingly enough, quite a few N64 games do not save progress at all, not even with a controller pak. We're including these games for the sake of completeness, in case you wondered why some games are not on the above lists. A few of these listed games at least support saving the old fashioned way with passwords.

Batman Beyond: Return of the Joker
Charlie Blast's Territory (Password)
Clayfighter 63 1/3
Clayfighter: Sculptor's Cut
Dark Rift
Elmo's Letter Adventure
Elmo's Number Journey
Jeopardy!
Knife Edge: Nosegunner
Mace: The Dark Age
Magical Tetris Challenge
Monster Truck Madness (Password)
Mortal Kombat Trilogy
Powerpuff Girls: Chemical X Traction (Password)
Rugrats: Scavenger Hunt
South Park: Chef's Luv Shack
Transformers Beast Wars: Transmetals
War Gods
Wheel of Fortune

Written by Andrew Gorbaty and Daniel Falk, 2019.
