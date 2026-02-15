# CPAN-228 Assignment 0: Project Planning

<p align="center">
  <img src="https://raw.githubusercontent.com/LynnethTigsePena4240/Four-Loop/main/logo.png" alt="The 404s v.2.0 Logo" width="350">
</p>

| Item | Details |
| :--- | :--- |
| **Team Name** | The 404s v.2.0 |
| **Divine Peralta** | My name is Divine and I’m excited to expand my knowledge on creating web applications. |
| **Krishna Pushparajah** | I’m Krishna and I’ve always wanted to make a product management application. I’m excited to see it come together. |
| **Lena Mukhtar** | I am Lena and I’m looking forward to building the e-commerce application. |
| **Lynneth Tigse-Pena** | I'm Lynneth and I'm looking forward to making something new that I haven't coded before. |

---

## What We're Building
Our team is developing a **Category 1: E-Commerce Platform**. We decided to move away from projects similar to our previous work to challenge ourselves with a new category. The application is being built as a **Spring Boot** project, utilizing **JPA** for data management, **Thymeleaf** for the user interface, and **Spring Security** to handle protected access.

## Core Concept: How It Works
The platform functions by linking product management directly with real-world logistics:

* **Product Organization:** Every item in the system is tied to a specific *Brand*, making the catalog easy to navigate and filter.
* **Inventory & Distribution:** The system doesn't just list items; it tracks exactly how many units are in stock across different *Distribution Centres*.
* **Administrative Control:** A secure *Admin* entity allows authorized users to manage *Product* data and monitor the system.
* **User Experience:** The UI is designed around a clean dashboard where users can search for products, view *Inventory*, and manage their shopping *Carts*.

---

## Domain Model



Our database consists of 6 main entities to support the e-commerce workflow:
1. **Admin**: Secure access management.
2. **Product**: Detailed item listings.
3. **Brand**: Categorization of products.
4. **Cart**: User selection management.
5. **Inventory**: Stock level tracking.
6. **Distribution Centre**: Physical location management.

---

## UI Layout Design



We have designed a clean, grid-based UI for the following views:
* **Login Page**: Secure portal for administrators.
* **Home/Dashboard**: Summary of products, inventory, and centers.
* **Product Management**: Views for adding and searching for items.
* **Inventory & Centers**: Dedicated pages to manage stock across locations.
