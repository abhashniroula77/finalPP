<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://codex.wordpress.org/Editing_wp-config.php
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'newsapp' );

/** MySQL database username */
define( 'DB_USER', 'root' );

/** MySQL database password */
define( 'DB_PASSWORD', '' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         't2jw7OtRk&XX(-t 6rAXze&Oxw-a!zW8gQ}*p9eNg!OQ 40rw.F9fH+0b&%R[wdp' );
define( 'SECURE_AUTH_KEY',  'u<=Faz`g<7C()U.Z5`!*S<%W_MVlN%~U6eO)vh*L*-!75?$lP7Kef{aSVlk_6+/K' );
define( 'LOGGED_IN_KEY',    'TLtV>SHe5#<UrKHyyy6LeM[V>$7Z9.o@Rifv9IgSD/7{vm;-nG$F+?4g,~TxCUe*' );
define( 'NONCE_KEY',        'Lo[*%K4/~9Qd3Nd4>U1Dzw5j33FE357+ZZCpi(vW7/g_%M.t7oo/~b-fG>r(LPc8' );
define( 'AUTH_SALT',        'SIo+P+l9DUy{]FyObM@~Mo^D0*>+^>k#VDf6)fV<TNQ%sSR#v&Z]1o`5 Hp.Zz)5' );
define( 'SECURE_AUTH_SALT', 'P]qFqX~~J&^Sk~%%vyqv^xVlgf_I&byXPq^C@J_PNA42n9R1z.~@3A|UvCPD[,W.' );
define( 'LOGGED_IN_SALT',   '2PF{v=Rw:OhxQ<.NJ<na*uB_S-RU4J,2}2S2BkC:|tD+c.7ls}$/v*6*}]lVJn9|' );
define( 'NONCE_SALT',       'D=bCw%-Ak(E]mIk7HUwpu0<y[r75]V.drdF2P?K^+q{oW#}g:-~Gm=PxFWVctoVq' );

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the Codex.
 *
 * @link https://codex.wordpress.org/Debugging_in_WordPress
 */
define( 'WP_DEBUG', false );

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', dirname( __FILE__ ) . '/' );
}

/** Sets up WordPress vars and included files. */
require_once( ABSPATH . 'wp-settings.php' );
