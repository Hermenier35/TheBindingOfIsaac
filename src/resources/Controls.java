package resources;

import libraries.Keybinding;

public class Controls
{
	public static int goUp = Keybinding.keycodeOf('z');
	public static int goDown = Keybinding.keycodeOf('s');
	public static int goRight = Keybinding.keycodeOf('d');
	public static int goLeft = Keybinding.keycodeOf('q');
	
	public static int haut = Keybinding.keycodeOf(Keybinding.SpecialKeys.UP);
	public static int bas = Keybinding.keycodeOf(Keybinding.SpecialKeys.DOWN);
	public static int gauche = Keybinding.keycodeOf(Keybinding.SpecialKeys.LEFT);
	public static int droite = Keybinding.keycodeOf(Keybinding.SpecialKeys.RIGHT);
}
