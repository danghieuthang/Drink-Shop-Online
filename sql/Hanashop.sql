USE [master]
GO
/****** Object:  Database [HanaShop]    Script Date: 20/03/01 10:04:55 PM ******/
CREATE DATABASE [HanaShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HanaShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\HanaShop.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'HanaShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\HanaShop_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [HanaShop] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HanaShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HanaShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HanaShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HanaShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HanaShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HanaShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HanaShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HanaShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HanaShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HanaShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HanaShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HanaShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HanaShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HanaShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HanaShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HanaShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HanaShop] SET RECOVERY FULL 
GO
ALTER DATABASE [HanaShop] SET  MULTI_USER 
GO
ALTER DATABASE [HanaShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HanaShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HanaShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HanaShop] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [HanaShop] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'HanaShop', N'ON'
GO
ALTER DATABASE [HanaShop] SET QUERY_STORE = OFF
GO
USE [HanaShop]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 20/03/01 10:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[ID] [int] NOT NULL,
	[Name] [nvarchar](200) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Drink]    Script Date: 20/03/01 10:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Drink](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](200) NULL,
	[Image] [nvarchar](200) NULL,
	[Description] [text] NULL,
	[Price] [float] NULL,
	[CreateDate] [date] NULL,
	[CategoryID] [int] NULL,
	[Quantity] [int] NULL,
	[Status] [nvarchar](50) NULL,
 CONSTRAINT [PK_Food] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 20/03/01 10:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TransactionID] [int] NULL,
	[DrinkID] [int] NULL,
	[Quantity] [int] NULL,
	[Amount] [float] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transaction]    Script Date: 20/03/01 10:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transaction](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](50) NULL,
	[CreateDate] [date] NULL,
	[Amount] [float] NULL,
 CONSTRAINT [PK_Transaction] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 20/03/01 10:04:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Email] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](200) NULL,
	[Password] [nvarchar](200) NULL,
	[RoleID] [nvarchar](50) NULL,
	[Status] [nvarchar](50) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Category] ([ID], [Name]) VALUES (1, N'Soft Drink')
INSERT [dbo].[Category] ([ID], [Name]) VALUES (2, N'Beer')
INSERT [dbo].[Category] ([ID], [Name]) VALUES (3, N'Wine')
INSERT [dbo].[Category] ([ID], [Name]) VALUES (4, N'Water')
SET IDENTITY_INSERT [dbo].[Drink] ON 

INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (1, N'Trius Cabernet France 2011', N'images\wine_1.png', N'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Non eos inventore aspernatur voluptatibus ratione odit molestias molestiae, illum et impedit veniam modi sunt quas nam mollitia earum perferendis, dolorem. Magni.', 629, CAST(N'2020-03-01' AS Date), 3, 2, N'Inactive')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (2, N'Trius Cabernet France 2011', N'images\wine_1.png', N'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Non eos inventore aspernatur voluptatibus ratione odit molestias molestiae, illum et impedit veniam modi sunt quas nam mollitia earum perferendis, dolorem. Magni.', 700, CAST(N'2020-03-01' AS Date), 3, 25, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (3, N'Morgon 13', N'images\wine_4.png', N'Ruou xin', 500, CAST(N'2020-02-27' AS Date), 3, 4, N'Inactive')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (4, N'Heineken', N'images\Heineken.jpg', N'Heineken Lager beer, or simply Heineken is a pale lager beer with 5% alcohol by volume, Dutch brewer Heineken N.V. manufacturing. Heineken is famous for its characteristic green bottle and red star', 300, CAST(N'2020-02-28' AS Date), 2, 97, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (5, N'bia tuoi quoc te', N'images\wine_2.png', N'Bia chat luong cua vietj nam', 123, CAST(N'2020-02-25' AS Date), 2, 5, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (6, N'Bia tuoiu', N'images\wine_2.png', N'Bia Vn', 500, CAST(N'2020-02-25' AS Date), 2, 5, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (7, N'Ajo', N'images\wine_1.png', N'AAAAAAAAAAAAAAAAAA', 13, CAST(N'2020-02-25' AS Date), 1, 3, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (8, N'Sting Red', N'images\Sting red.jpg', N'Ingredients and uses:
Main ingredients: CO2 saturated water, cane sugar, acidity regulator, natural and synthetic flavor mixture, vitamins ...
Produced on modern lines by advanced technology, under the strict supervision and inspection of experts in the food technology industry.
With taurine, inositol, vitamin B, combined with ginseng, Sting energy drink brings abundant energy to the body, helping you always full of energy, overcome fatigue.', 300, CAST(N'2020-02-25' AS Date), 1, 100, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (9, N'Sting Gold', N'images\Sting gold.jpg', N'Ingredients and uses:
Main ingredients: CO2 saturated water, cane sugar, acidity regulator, natural and synthetic flavor mixture, vitamins ...
Produced on modern lines by advanced technology, under the strict supervision and inspection of experts in the food technology industry.
With taurine, inositol, vitamin B, combined with ginseng, Sting energy drink brings abundant energy to the body, helping you always full of energy, overcome fatigue.', 300, CAST(N'2020-03-01' AS Date), 1, 195, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (10, N'7 Up', N'images\7Up.jpg', N'Water saturated with CO2, sugar cane, natural acidity regulator (330, 331iii, 296).', 100, CAST(N'2020-02-27' AS Date), 1, 100, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (11, N'Allan Scott Sauvignon Blanc', N'images\Allan Scott Sauvignon Blanc.jpg', N'Dry, fresh, delicious wine. This is all you would expect from the classic Marlborough Sauvignon Blanc manufacturer. Grapefruit and sharp minerals. Dry taste complete with acidity.', 500, CAST(N'2020-02-27' AS Date), 3, 100, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (12, N'Black Label', N'images\Black Label.jpg', N'So delicate and charming! A taste that brings a feeling far beyond expectation. Delicate tastes become more attractive over time. Black Label leaves a slight aroma of smoky aroma and a dry fruity and vanilla flavor.
A magical combination of more than 40 different fine whiskeys at least 12 years old, each bringing a unique touch to the Black Label. More than that, Black Label is recognized by whiskey writer and expert Jimmy Murray as the standard of premium whiskey.
Fine whiskeys vary at least 12 years old, each bringing a unique touch to the Black Label. More than that, Black Label is recognized by whiskey writer and expert Jimmy Murray as the standard of premium whiskey.', 600, CAST(N'2020-02-27' AS Date), 3, 100, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (13, N'Champagne Italy BOTTEGA GOLD', N'images\Champagne Italia Bottega Gold.jpg', N'The wine possesses a pale greenish-yellow tone derived from the same Glera grape, you will be impressed at the first sight by that attractive color. Pour out of a glass and shake well, surely sprinkling into your nose is the aroma of ripe fruits and flowers. The sweet, fresh fruit flavor gives you a sense of great refreshment. In addition, you will also feel the taste of apple, lemon, peach, watermelon, plum. Average citric acid from fruits, balanced tannins with 11% concentration and impressive effervescence like champagne will make you awake and refreshed when enjoying small sips.', 600, CAST(N'2020-02-27' AS Date), 3, 100, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (14, N'Cocacola', N'images\Cocacola.jpg', N'Coca Cola soft drink is a unique combination of traditional coca cola flavor and concentrated coffee to create a unique and new fragrance for people to enjoy.
The product is suitable for office workers, helps to awaken the ability to concentrate in the middle of the morning or after lunch break, arouse the spirit of learning, working excited
Products that help provide energy for a long day of activity.', 100, CAST(N'2020-02-27' AS Date), 1, 100, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (15, N'Ha Noi Beer', N'images\Hahoi.jpg', N'For domestic beer lovers, Hanoi bottled beer is a familiar choice in dining gatherings, whether it is with family dinners or with friends. Bottled beer in Hanoi is trusted not only by its stable quality, but also because it is a reputable Vietnamese brand associated with Hanoi capital and widely known by international friends.
Oriented as the core product of Hanoi beer brand in general, Hanoi beer bottle has an annual output accounting for 70% of the total output of HABECO''s products. The reason why Hanoi bottle beer has a strong position in the Northern provinces and cities market is because of its attractive bright yellow beer color, durable foam, fine white, harmonious bitterness, mellow, and aftertaste deposition.
Confident with the quality and brand power of this product, HABECO currently exports 450ml Hanoi beer to markets in Taiwan, Korea, and Australia.', 200, CAST(N'2020-02-28' AS Date), 2, 96, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (16, N'LaQuita', N'images\LaQuinta.png', N'La Quinta Syrah Port is considered a ruby port due to its dark red color. Paso Robles provides an ideal climate for the ripening of Syrah grapes to 25-27Âº Brix. Each variety is then harvested and fermented separately. At approximately 8-10Âº Brix, California brandy is added t', 400, CAST(N'2020-02-28' AS Date), 3, 3, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (17, N'Lavie', N'images\Lavie.png', N'Lavie mineral water brand is famous all over the world, with quality and safety, so the product is trusted by many people. In Vietnam, Lavie mineral water known since 1992, produced and distributed coordinated by the Neslte Group.
Lavie products are exploited and bottled at the source of no chemical treatment in the process and the production system is completely tight and fully automatic, so it keeps the purity of water, with very suitable natural mineral content. suitable for Vietnamese people', 30, CAST(N'2020-02-28' AS Date), 4, 49, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (18, N'Mirinda', N'images\Mirinda.jpg', N'Carbonated soft drinks are a type of beverage usually containing saturated carbon dioxide water, sweeteners, and often flavored. Sweeteners can be sugars, high-fructose corn syrup, fruit juices, alternative sweeteners commonly found in "no sugar" or combinations of the above.', 50, CAST(N'2020-02-28' AS Date), 1, 18, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (19, N'Redbull', N'images\Redbull.jpg', N'Red Bull is an energy drink owned by the Austrian company Red Bull GmbH. The drink was invented by the Austrian businessman Dietrich Mateschitz in 1987 and Red Bull''s market share dominates the energy drink market in the world, with about 3 billion cans sold each year.', 100, CAST(N'2020-02-28' AS Date), 1, 99, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (20, N'Pepsi', N'images\Pepsi.jpg', N'Pepsi soft drink was first created from carbonate water, sugar, vanilla and a bit of very simple cooking oil to become a drink that helps you digest easily and was named Brad''s drink that Mr. Bradham sought. came up with this recipe in 1886.', 80, CAST(N'2020-02-28' AS Date), 1, 123, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (21, N'Saka', N'images\Saka.jpg', N'Drink for every family', 80, CAST(N'2020-03-01' AS Date), 4, 48, N'Active')
INSERT [dbo].[Drink] ([ID], [Name], [Image], [Description], [Price], [CreateDate], [CategoryID], [Quantity], [Status]) VALUES (22, N'Tiger', N'images\tiger.jpg', N'Beer Tiger', 100, CAST(N'2020-03-01' AS Date), 2, 19, N'Active')
SET IDENTITY_INSERT [dbo].[Drink] OFF
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (1, 7, 1, 1, 629)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (2, 7, 7, 2, 26)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (3, 8, 5, 2, 246)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (4, 8, 7, 1, 13)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (5, 9, 1, 3, 1887)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (6, 9, 7, 1, 13)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (7, 10, 3, 1, 500)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (8, 11, 4, 2, 246)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (9, 12, 17, 1, 30)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (10, 12, 4, 3, 900)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (11, 12, 15, 4, 800)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (12, 13, 16, 3, 1200)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (13, 13, 19, 1, 100)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (14, 14, 17, 4, 120)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (15, 14, 18, 2, 100)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (16, 15, 21, 1, 80)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (17, 15, 9, 3, 900)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (18, 16, 9, 1, 300)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (19, 17, 21, 1, 80)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (20, 17, 9, 1, 300)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (21, 18, 7, 1, 13)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (22, 19, 16, 2, 800)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (23, 20, 16, 4, 1600)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (24, 21, 16, 2, 800)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (25, 22, 17, 1, 30)
INSERT [dbo].[Order] ([ID], [TransactionID], [DrinkID], [Quantity], [Amount]) VALUES (26, 23, 22, 1, 100)
SET IDENTITY_INSERT [dbo].[Order] OFF
SET IDENTITY_INSERT [dbo].[Transaction] ON 

INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (4, N'dhthang@gmail.com', CAST(N'2019-12-12' AS Date), 123)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (5, N'dhthang@gmail.com', CAST(N'2019-12-12' AS Date), 134)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (7, N'dhthang@gmail.com', CAST(N'2020-02-24' AS Date), 655)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (8, N'dhthang@gmail.com', CAST(N'2020-02-25' AS Date), 259)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (9, N'dhthang@gmail.com', CAST(N'2020-02-25' AS Date), 1900)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (10, N'dhthang@gmail.com', CAST(N'2020-02-25' AS Date), 500)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (11, N'dhthang1998@gmail.com', CAST(N'2020-02-26' AS Date), 246)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (12, N'dhthang9x02@gmail.com', CAST(N'2020-02-28' AS Date), 1730)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (13, N'dhthang9x02@gmail.com', CAST(N'2020-02-28' AS Date), 1300)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (14, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 220)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (15, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 980)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (16, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 300)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (17, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 380)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (18, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 13)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (19, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 800)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (20, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 1600)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (21, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 800)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (22, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 30)
INSERT [dbo].[Transaction] ([ID], [Email], [CreateDate], [Amount]) VALUES (23, N'dhthang1998@gmail.com', CAST(N'2020-03-01' AS Date), 100)
SET IDENTITY_INSERT [dbo].[Transaction] OFF
INSERT [dbo].[User] ([Email], [Name], [Password], [RoleID], [Status]) VALUES (N'admin@hanashop.com', N'Admin', N'1', N'Admin', N'Active')
INSERT [dbo].[User] ([Email], [Name], [Password], [RoleID], [Status]) VALUES (N'dhloi@gmail.com', N'Loi', N'1', N'User', N'New')
INSERT [dbo].[User] ([Email], [Name], [Password], [RoleID], [Status]) VALUES (N'dhthang@gmail.com', N'Dang Hieu Thang', N'1', N'User', N'Active')
INSERT [dbo].[User] ([Email], [Name], [Password], [RoleID], [Status]) VALUES (N'dhthang1998@gmail.com', N'Bạch Đế', N'1', N'User', N'Active')
INSERT [dbo].[User] ([Email], [Name], [Password], [RoleID], [Status]) VALUES (N'dhthang9x02@gmail.com', N'Trần Trường Sinh', N'1', N'User', N'Active')
INSERT [dbo].[User] ([Email], [Name], [Password], [RoleID], [Status]) VALUES (N'hanashop@gmail.com', N'HanaShop Member', N'1', N'User', N'New')
ALTER TABLE [dbo].[Drink]  WITH CHECK ADD  CONSTRAINT [FK_Food_Category] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([ID])
GO
ALTER TABLE [dbo].[Drink] CHECK CONSTRAINT [FK_Food_Category]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Food] FOREIGN KEY([DrinkID])
REFERENCES [dbo].[Drink] ([ID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Food]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Transaction] FOREIGN KEY([TransactionID])
REFERENCES [dbo].[Transaction] ([ID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Transaction]
GO
ALTER TABLE [dbo].[Transaction]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_User] FOREIGN KEY([Email])
REFERENCES [dbo].[User] ([Email])
GO
ALTER TABLE [dbo].[Transaction] CHECK CONSTRAINT [FK_Transaction_User]
GO
USE [master]
GO
ALTER DATABASE [HanaShop] SET  READ_WRITE 
GO
