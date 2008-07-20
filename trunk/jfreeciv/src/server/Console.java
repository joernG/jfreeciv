package server;

import static port.util.*;
import static server.Erfc_status.*;
import utility.Flog_callback_fn;
import static utility.Log.*;

public class Console {
	static boolean console_show_prompt = false;
	static boolean console_prompt_is_showing = false;
	static boolean console_rfcstyle = false;
	// #ifdef HAVE_LIBREADLINE
	// static boolean readline_received_enter = true;
	// #else
	// static int con_dump(Erfc_status rfc_status, final String message);
	// #endif

	/***************************************************************************
	 * Function to handle log messages. This must match the log_callback_fn
	 * typedef signature.
	 **************************************************************************/
	static Flog_callback_fn con_handle_log = new Flog_callback_fn() {
		public void log_callback_fn(int level, String message) {
			if (console_rfcstyle) {
				con_write(Erfc_status.C_LOG_BASE.getIndex() + level, "%s",
						message);
			} else {
				con_write(Erfc_status.C_LOG_BASE.getIndex() + level, "%d: %s",
						level, message);
			}
		}
	};

	/***************************************************************************
	 * Print the prompt if it is not the last thing printed.
	 **************************************************************************/
	static void con_update_prompt() {
		if (console_prompt_is_showing || !console_show_prompt)
			return;

		// #ifdef HAVE_LIBREADLINE
		// if (readline_received_enter) {
		// readline_received_enter = false;
		// } else {
		// rl_forced_update_display();
		// }
		// #else
		con_dump(Erfc_status.C_READY, "> ");
		con_flush();
		// #endif

		console_prompt_is_showing = true;
	}

	/***************************************************************************
	 * Initialize logging via console.
	 **************************************************************************/
	void con_log_init(final String log_filename, int log_level) {
		boolean has_file = (log_filename != null && log_filename.length() > 0);

		log_init(log_filename, log_level, has_file ? null : con_handle_log);
	}

	// #ifndef HAVE_LIBREADLINE
	/***************************************************************************
	 * Write to console without line-break, don't print prompt.
	 **************************************************************************/
	static int con_dump(Erfc_status rfc_status, Object... message) {
		// static char buf[MAX_LEN_CONSOLE_LINE];
		// va_list args;
		//
		// va_start(args, message);
		// my_vsnprintf(buf, sizeof(buf), message, args);
		// va_end(args);
		String buf = String.format("%s", message);

		if (console_prompt_is_showing) {
			fc_printf("\n");
		}
		if ((console_rfcstyle) && (rfc_status.getIndex() >= 0)) {
			fc_printf("%.3d %s", rfc_status, buf);
		} else {
			fc_printf("%s", buf);
		}
		console_prompt_is_showing = false;
		return buf.length();
	}

	// #endif

	/***************************************************************************
	 * Write to console and add line-break, and show prompt if required.
	 **************************************************************************/
	public static void con_write(Erfc_status rfc_status, final String message) {
		// static char buf[MAX_LEN_CONSOLE_LINE];
		// va_list args;
		//
		// va_start(args, message);
		// my_vsnprintf(buf, sizeof(buf), message, args);
		// va_end(args);
		String buf = String.format("%s", message);

		con_puts(rfc_status, buf);
	}
	public static void con_write(Erfc_status rfc_status, final String format, Object... message) {
		con_write(rfc_status, String.format(format, message));
	}

	public static void con_write(int level, String format, Object... message) {
		// TODO Auto-generated method stub
		String buf = String.format(format, message);
		con_puts(C_LOG_BASE, buf);
	}

	/***************************************************************************
	 * Write to console and add line-break, and show prompt if required. Same as
	 * con_write, but without the format string stuff. The real reason for this
	 * is because __attribute__ complained with
	 * con_write(Erfc_status.C_COMMENT,"") of "warning: zero-length format
	 * string"; this allows con_puts(Erfc_status.C_COMMENT,"");
	 **************************************************************************/
	static void con_puts(Erfc_status rfc_status, final String str) {
		if (console_prompt_is_showing) {
			fc_printf("\n");
		}
		if ((console_rfcstyle) && (rfc_status.getIndex() >= 0)) {
			fc_printf("%.3d %s\n", rfc_status, str);
		} else {
			fc_printf("%s\n", str);
		}
		console_prompt_is_showing = false;
		con_update_prompt();
	}

	/***************************************************************************
	 * Ensure timely update.
	 **************************************************************************/
	static void con_flush() {
		// fflush(stdout);
		System.out.flush();
	}

	/***************************************************************************
	 * Set style.
	 **************************************************************************/
	void con_set_style(boolean i) {
		console_rfcstyle = i;
		if (console_rfcstyle)
			con_puts(C_OK, "Ok. RFC-style set.");
		else
			con_puts(C_OK, "Ok. Standard style set.");
	}

	/***************************************************************************
	 * Returns rfc-style.
	 **************************************************************************/
	boolean con_get_style() {
		return console_rfcstyle;
	}

	/***************************************************************************
	 * Initialize prompt; display initial message.
	 **************************************************************************/
	static boolean first = true;

	void con_prompt_init() {
		if (first) {
			con_puts(Erfc_status.C_COMMENT, "");
			con_puts(Erfc_status.C_COMMENT,
					"For introductory help, type 'help'.");
			first = false;
		}
	}

	/***************************************************************************
	 * Make sure a prompt is printed, and re-printed after every message.
	 **************************************************************************/
	void con_prompt_on() {
		console_show_prompt = true;
		con_update_prompt();
	}

	/***************************************************************************
	 * Do not print a prompt after log messages.
	 **************************************************************************/
	void con_prompt_off() {
		console_show_prompt = false;
	}

	/***************************************************************************
	 * User pressed enter: will need a new prompt
	 **************************************************************************/
	void con_prompt_enter() {
		console_prompt_is_showing = false;
		// #ifdef HAVE_LIBREADLINE
		// readline_received_enter = true;
		// #endif
	}

	/***************************************************************************
	 * Clear "user pressed enter" state (used in special cases).
	 **************************************************************************/
	void con_prompt_enter_clear() {
		console_prompt_is_showing = true;
		// #ifdef HAVE_LIBREADLINE
		//		readline_received_enter = false;
		//		#endif
	}
}