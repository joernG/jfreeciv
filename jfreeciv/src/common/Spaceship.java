package common;

import common.play_spaceship.spaceship_state;

import server.spaceace.player_spaceship;

public class Spaceship {
	// #include "shared.h" /* true, false */

	// final struct sship_part_info
	// structurals_info[player_spaceship.NUM_SS_STRUCTURALS] = {
	public static final sship_part_info structurals_info[] = {
			new sship_part_info(19, 13, -1), /* -1 means none required */
			new sship_part_info(19, 15, 0), new sship_part_info(19, 11, 0),
			new sship_part_info(19, 17, 1), new sship_part_info(19, 9, 2),
			new sship_part_info(19, 19, 3), new sship_part_info(17, 9, 4),
			new sship_part_info(17, 19, 5), new sship_part_info(21, 11, 2),
			new sship_part_info(21, 17, 3), new sship_part_info(15, 9, 6),
			new sship_part_info(15, 19, 7), new sship_part_info(23, 11, 8),
			new sship_part_info(23, 17, 9), new sship_part_info(13, 9, 10),
			new sship_part_info(13, 19, 11), new sship_part_info(11, 9, 14),
			new sship_part_info(11, 19, 15), new sship_part_info(9, 9, 16),
			new sship_part_info(9, 19, 17), new sship_part_info(7, 9, 18),
			new sship_part_info(7, 19, 19), new sship_part_info(19, 7, 4),
			new sship_part_info(19, 21, 5), new sship_part_info(19, 5, 22),
			new sship_part_info(19, 23, 23), new sship_part_info(21, 5, 24),
			new sship_part_info(21, 23, 25), new sship_part_info(23, 5, 26),
			new sship_part_info(23, 23, 28), new sship_part_info(5, 9, 20),
			new sship_part_info(5, 19, 21) };

	// public static final sship_part_info
	// components_info[player_spaceship.NUM_SS_COMPONENTS] = {
	public static final sship_part_info components_info[] = {
			new sship_part_info(21, 13, 0), new sship_part_info(24, 13, 12),
			new sship_part_info(21, 15, 1), new sship_part_info(24, 15, 13),
			new sship_part_info(21, 9, 8), /* or 4 */
			new sship_part_info(24, 9, 12), new sship_part_info(21, 19, 9), /* or 5 */
			new sship_part_info(24, 19, 13), new sship_part_info(21, 7, 22),
			new sship_part_info(24, 7, 28), new sship_part_info(21, 21, 23),
			new sship_part_info(24, 21, 29), new sship_part_info(21, 3, 26),
			new sship_part_info(24, 3, 28), new sship_part_info(21, 25, 27),
			new sship_part_info(24, 25, 29) };

	// public static final sship_part_info
	// modules_info[player_spaceship.NUM_SS_MODULES] = {
	public static final sship_part_info modules_info[] = {
			new sship_part_info(16, 12, 0), new sship_part_info(16, 16, 1),
			new sship_part_info(14, 6, 10), new sship_part_info(12, 16, 15),
			new sship_part_info(12, 12, 14), new sship_part_info(14, 22, 11),
			new sship_part_info(8, 12, 18), new sship_part_info(8, 16, 19),
			new sship_part_info(6, 6, 20), new sship_part_info(4, 16, 31),
			new sship_part_info(4, 12, 30), new sship_part_info(6, 22, 21) };


	/***************************************************************************
	 * Count the number of structurals placed; that is, in ship.structure[]
	 **************************************************************************/
	int num_spaceship_structurals_placed(player_spaceship ship) {
		int i, num = 0;
		for (i = 0; i < player_spaceship.NUM_SS_STRUCTURALS; i++) {
			if (ship.structure[i])
				num++;
		}
		return num;
	}
}