# European Dynamics - Code.hub DVD Store Mid-Season Hackathon
This project will host the code of the end-season hackathon.

## Description
The document describes the goal of the Hackathon taking place in the 11th of February dealing with the management of a DVD Store. The aim of this document is to gather, analyze and give an insight of the DVD Store management system. Nevertheless, it also concentrates on the capabilities required by management and their customer needs.

###	Project Perspective
DVD Store management system is a platform managing DVDs organized in a series of categories, along with their corresponding rentals. It consists of three sub-systems; DVD Store, Administration/Management and Reporting Services.

Following mid-season hackathon where we implemented required functionality using a file-based approach, we are asked to revisit this implementation and introduce a more modern approach utilizing RESTful services and data oriented technologies. 

Based on the fact that we are not covering user interface technologies, features will be considered complete upon successful return of a valid response containing JSON formatted content per action.

Details will be given inside the classroom before hackathon begins.


### Features
**Check point 1**
1. Checkout Github repository https://github.com/costasgiannacoulis/ed-2019-hackathon-endseason.git
2. Copy/Move parts that of the previous implementation you consider still valid.
3. Introduce Spring Data JPA and H2 database.
4. Annotate accordingly entities and define database schema.
5. Define relations among entities.

**Check point 2**
1. Introduce new repository layer substituting existing. Perform any changes needed in the service layer in order to maintain existing service layer functionality.
2. Enable transaction support to actions needing it.
3. Load sample data to database by either extending existing mechanism or creating a new one.

**Check point 3**
1. Expose functionality as Rest Services. Standard exposed functionality should at least expose CRUD actions per entity. On top expose specific business actions (e.g. rent a DVD for a given customer).
2. Implement one unified response format covering both successful and erroneous calls. Make sure you also return corresponding HTTP statuses.

**Check point 4**
1. Implement specific logging policy (e.g. change logging mechanism implementation, and/or have specific files contain specific logs, rotation policy).
2. Implement caching (server-side, client-side) wherever needed and prevent content from being cached wherever applicable.
3. Generate reports of specific format (XLSX). We need at least two reports.

Keep in mind that during hackathon, all participants will start from the same shared codebase in every check point.

### Operating Environment
DVD Store should be able to run on every operating system supporting Java 8 or greater.

Its underlying data repository should be based on Java data structures.
