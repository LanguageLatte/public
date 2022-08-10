import praw
import os

from enum import Enum
from constants import *

class Reply(Enum):
    BASE_REPLY = 1

class AnkiHelpBot:
    responses: dict[Reply, str]
    reddit: praw.Reddit

    def __init__(self) -> None:
        self.responses = self.loadReplies()
        self.reddit = self.connect()


    def main(self):
        subreddit = self.reddit.subreddit(CONST_SUBREDDIT)
        for submission in subreddit.stream.submissions(skip_existing=True):
            self.process_submission(submission)

    def process_submission(self, submission):
        print(self.responses[Reply.BASE_REPLY])
        if submission.link_flair_text == "Question":
            submission.reply(body=self.responses[Reply.BASE_REPLY])


    def loadReplies(self) -> dict[Reply, str]:
        response: dict[Reply, str] = {}

        with open('replies/baseReply.md', 'r') as baseReply:
            baseReplyText = baseReply.read()
            response[Reply.BASE_REPLY] = baseReplyText
        
        return response

    def connect(self) -> praw.Reddit:
        return praw.Reddit(
            client_id=os.getenv(CONST_CLIENT_ID_ENV),
            client_secret=os.getenv(CONST_CLIENT_SECRET_ENV),
            redirect_uri="https://github.com/LanguageLatte/public",
            password=os.getenv(CONST_PASSWORD_ENV),
            user_agent=CONST_USER_AGENT,
            username=CONST_USERNAME,
        )

if __name__ == "__main__":
    ankiHelpBot = AnkiHelpBot()
    ankiHelpBot.main()