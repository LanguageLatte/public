import praw
import os

CONST_REPLY = """
I am a Bot 


**Read the User Manual**. There is a good chance they answer your questions. 

* [Anki Desktop](https://docs.ankiweb.net/)
* [Anki Android](https://docs.ankidroid.org/)
* [Anki IOS](https://docs.ankimobile.net/)

**Anki Settings Quickstart** Courtesy of `u/AnKingMed`

* [Anki V3 Settings - 40 min](https://youtu.be/Eo1HbXEiJxo)


**Recommended Reading!**

* [How to make good cards](https://www.supermemo.com/en/archives1990-2015/articles/20rules)
* [How to make good cards in depth](https://andymatuschak.org/prompts/)
* [Science of Spaced Repetition](https://www.gwern.net/Spaced-repetition)

**Other Help**

* Medical school student, checkout r/medicalschoolanki
* Addons: https://ankiweb.net/shared/addons/
* Shared Decks: https://ankiweb.net/shared/decks/

[Bot feedback here](https://github.com/LanguageLatte/public)
"""

CONST_USERNAME = "AnkiHelpBot"
CONST_USER_AGENT = "AnkiHelpBot (by u/LanguageLatte)"
CONST_CLIENT_ID_ENV = "ANKI_REDDIT_BOT_CLIENT_ID"
CONST_CLIENT_SECRET_ENV = "ANKI_REDDIT_BOT_CLIENT_SECRET"
CONST_PASSWORD_ENV = "ANKI_REDDIT_BOT_PASSWORD"
CONST_SUBREDDIT = "test"

def main():
    reddit = praw.Reddit(
        client_id=os.getenv(CONST_CLIENT_ID_ENV),
        client_secret=os.getenv(CONST_CLIENT_SECRET_ENV),
        redirect_uri="https://github.com/LanguageLatte/public",
        password=os.getenv(CONST_PASSWORD_ENV),
        user_agent=CONST_USER_AGENT,
        username=CONST_USERNAME,
    )

    subreddit = reddit.subreddit(CONST_SUBREDDIT)

    for submission in subreddit.stream.submissions(skip_existing=True):
        process_submission(submission)

def process_submission(submission):
    if submission.link_flair_text == "Question":
        submission.reply(body=CONST_REPLY)

if __name__ == "__main__":
    main()