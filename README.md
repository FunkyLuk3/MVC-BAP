# MVC-BAP
Rendu du projet noté Balle aux Prisonniers

Le projet a été réalisé avec Maven sur InteliJ IDEA Community. La bibliothèque utilisée est javafx, prise en charge par Maven.

## Description :

C'est un jeu de balle au prisonnier, où deux équipes de 3 joueurs s'affrontent. Chaque équipe a un joueur contrôlé par un humain et deux bots.
  
Si un joueur tient la balle, il a la possibilité de la lancer dans la direction souhaitée pour éliminer des membres de l'équipe adverse. 
Dans le cas où un joueur se fait éliminer, il prend le contrôle d'un des bots encore en vie de son équipe et la portie continue.
Les joueurs sont cantonnés à leur moitié de terrain.
  
La balle perd de la vitesse une fois lancée, et s'arrête elle devient trop lente.
Elle peut alors être ramassée par un joueur qui passe à proximité. Si la balle touche le côté gauche ou droite du terrain, elle rebondi et gagne aussi de la vitesse. C'est un choix délibéré afin d'encourager des tirs ambitieux et surprenants.
Si la balle sort par le haut ou le bas du terrain, elle est remise au centre de la zone de l'équipe correspondante, dans le but d'être ramassée par l'équipe adverse quand un tir est manqué.

## Le faire fonctionner chez vous :

Voici les étapes que nous avons suivies afin d'avoir un environnement dans lequel le projet fonctionner :
- Installer InteliJ IDEA Community
- Installer, depuis **File -> Project Structure -> Project Settings -> Project** le JDK 17.0 ou plus récent.
- Dans **Run -> Edit Configurations**, ajouter une configuration Maven et dans le command line, mettre ```javafx:run```
	
On peut maintenant build et run le projet depuis InteliJ IDEA Community.
	
Pour lancer le jeu :
- Installer Maven
- Installer un JDK (17 au minimum)
- Dans le dossier **MVC-BAP/**, lancer une console et utiliser la commande ```mvn javafx:run```

