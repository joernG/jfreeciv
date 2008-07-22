package common.connection;
/* where the connection is in the authentication process */
public enum auth_status {
  AS_NOT_ESTABLISHED,// = 0,
  AS_FAILED,
  AS_REQUESTING_NEW_PASS,
  AS_REQUESTING_OLD_PASS,
  AS_ESTABLISHED
};