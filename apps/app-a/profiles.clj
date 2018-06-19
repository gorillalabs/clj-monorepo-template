;; Please pay attention:
;; - do not add this file to version control (as it contains user secrets)
;; - this file should contain environment variables to use on your local
;;   development system. On your shared environments, use a secret storage
;;   for that (like Vault).
{:profiles/dev {:env {:env                            "LOCAL"

                      :oath-github-secret      "..."
                      :oath-stack-secret       "..."

                      :jwt-cookie-domain       ".app.localhost"
                      :private-key-passphrase  "..."

                      :tls-keystore-passphrase "..."
                      }}}