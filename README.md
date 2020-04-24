# Tip Calculator 

## Raaisa Moktader

**Tippy** computes the tip and total amount for a bill. The app uses the base amount and tip percentage to calculate the amount owed, and it also describes the quality of service based on the tip. The app also allows the user to specify a budget that the user would like
to stay under for the new amount (tip and total combined), alerting the user through a color change of the total to indicate when the
budget has been exceeded.

Time spent: **2** hours spent in total

## Functionality 

The following **required** functionality is completed:

* [X] User can enter in a bill amount (total amount to tip on)
* [X] User can enter a tip percentage (what % the user wants to tip).
* [X] The tip and total amount are updated immediately when any of the inputs changes.
* [X] The user sees a label or color update based on the tip amount. 

The following **extensions** are implemented:

* [X] Custom colors palette selected
* [X] User can add a budget (max amount the user is willing to spend)
* [X] The total will be highlighted red if it is over the budget (if a budget is specified)
* [X] Added emojis to the tip description

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src="https://i.imgur.com/4qN4AFB.gif" title='Tip Calculator' height = '800' width='' alt='Video Walkthrough' />

## Notes

One challenge that I noticed was that there was no constraint on how long the number a user entered into the 
"Base" or the "Budget" values, so the code had some funky functionality when a user entered something like "1000000000000000000000";
the zeros would just wrap down to the next line and overlap the text views underneath it. I didn't get a chance to solve this issue,
but this is something I look forward to exploring for the upcoming assignments!

## License

    Copyright [2020] [Raaisa Moktader]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
