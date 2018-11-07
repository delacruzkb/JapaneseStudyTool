# JapaneseStudyTool

Just something made to help me practice japanese based on our textbooks.

Everything stored in the Database is a Term class.

CSV format for initial loading:
japanese(hiragana),english,japanese(kanji),type,lesson,requiredKanji

Story mode:

+half of the screen dedicated to loading google translate page in a webView

+half of the screen dedicated to presenting nouns/verbs/adjectives/grammar points/other expressions

+Goal: write a story/scenario with the given information in japanese, then see if when translated back into english, was it the intended story?

FlashCard Mode:

+Simple flashcard for testing terms in japanese (hiragana or kanji) to english and vice versa

Stroke practice:
+ Practice the strokes for kanji

Add Term:

+Used to add new terms

+searches database for similar terms based on japanese and english fields of each term
++ Populates a list of similar terms with an option to merge the new term into an existing term

Edit Term:
+ search for a term to edit based on certain fields


Load initial terms:
+ Loads a set of terms based on our textbook and other expressions the professor has mentioned off-hand


KanjiStroke Practice:
+ area for writing kanji on phone
+ the kanji is hidden at first, at first you are presented with either the english or hiragana meaning of the kanji


Foundations of Drawing code provided by:

https://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-interface-creation--mobile-19021
