# NHN Mart Customer Center
NHN Mart software is generating successful sales. In this situation, a customer center for receiving customer complaints is provided as a web application. Let's implement it appropriately based on the following specifications to solve the problem.

## Customer
- Customer account information already exists.
  - No need to create a separate membership.
- Attributes such as ID, password, and name exist.

## Login/Logout
- Access to the customer page after logging in.
- Exception occurs when login fails.

## My Inquiries List
- On the customer page, you can view the list of inquiries you have submitted.
- The inquiry can be viewed all at once (no pagination).
- When you click the [Inquiry] button, you can submit an inquiry.
- The list displays the title, category, creation date, and answer status.
- Clicking on the title displays the details of the inquiry.

## Inquiry Details
- If there is no answer, the title, category, and body are displayed, and it is marked as no answer.
- If there is an answer
  - The customer can also see the answer content, answer date, and CS staff in the inquiry details.

## Submit Inquiry
- There are title, category, body, date of creation, writer, and attachments.
  - Category: Complaints, Suggestions, Refunds/Exchanges, Compliments, Other Inquiries
- You can upload N attachments. Only images (gif, jpg, jpeg, png) are allowed for attachments.
- You can view your own inquiries. Only your inquiries can be viewed.
- When the inquiry is successful, the history of my inquiries is displayed again.
- The most recently submitted inquiry is located at the top.
- There are [Confirm] and [Cancel] buttons.
  - If the title and body are empty when you press the [Confirm] button, a validation exception occurs.
    - Title: 2~200 characters or bytes
    - Body: 0~40,000 characters or bytes
- If you press the [Cancel] button, you will be directed to the main (my inquiry list) page.

## CS Staff
- CS staff already exist.
  - No need to create a UI for adding CS staff.

## Customer Inquiry List
- After logging in to the CS administrator page, you can answer customer inquiries that have not been answered.
- On the CS administrator page, you can only view the list of "customer inquiries that have not been answered".
  - Once a response is made, it cannot be answered again.


## Respond to Customer Inquiries
- Select one inquiry and answer appropriately.
  - The inquiry details and answer text area are displayed.
  - When you click on an image uploaded by a customer, you can view it in a new window or pop-up. Alternatively, downloading is also possible.
  - When answering, the customer can also see the answer content, answer date, and answer CS staff in the inquiry details.
- There are [Reply] and [Cancel] buttons.
  - If the answer is empty when you press the [Confirm] button, a validation exception occurs.
    - Answer: 1~40,000 characters or bytes
  - If you press the [Cancel] button, you will be directed to the main (customer inquiry list) page.
