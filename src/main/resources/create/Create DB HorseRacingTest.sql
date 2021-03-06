USE [master]
GO
/****** Object:  Database [HorseRacingTest]    Script Date: 21.02.2021 8:59:34 ******/
CREATE DATABASE [HorseRacingTest]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HorseRacingTest', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HorseRacingTest.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HorseRacingTest_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HorseRacing_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HorseRacingTest] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HorseRacingTest].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HorseRacingTest] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HorseRacingTest] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HorseRacingTest] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HorseRacingTest] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HorseRacingTest] SET ARITHABORT OFF 
GO
ALTER DATABASE [HorseRacingTest] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HorseRacingTest] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HorseRacingTest] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HorseRacingTest] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HorseRacingTest] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HorseRacingTest] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HorseRacingTest] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HorseRacingTest] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HorseRacingTest] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HorseRacingTest] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HorseRacingTest] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HorseRacingTest] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HorseRacingTest] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HorseRacingTest] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HorseRacingTest] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HorseRacingTest] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HorseRacingTest] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HorseRacingTest] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HorseRacingTest] SET  MULTI_USER 
GO
ALTER DATABASE [HorseRacingTest] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HorseRacingTest] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HorseRacingTest] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HorseRacingTest] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HorseRacingTest] SET DELAYED_DURABILITY = DISABLED 
GO
USE [HorseRacingTest]
GO
/****** Object:  User [RRA]    Script Date: 21.02.2021 8:59:34 ******/
CREATE USER [RRA] FOR LOGIN [RRA] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [horse_racing_employer]    Script Date: 21.02.2021 8:59:34 ******/
CREATE USER [horse_racing_employer] FOR LOGIN [BUILTIN\Пользователи] WITH DEFAULT_SCHEMA=[db_datareader]
GO
/****** Object:  DatabaseRole [PLAYER]    Script Date: 21.02.2021 8:59:34 ******/
CREATE ROLE [PLAYER]
GO
/****** Object:  DatabaseRole [EMPLOYER]    Script Date: 21.02.2021 8:59:34 ******/
CREATE ROLE [EMPLOYER]
GO
/****** Object:  DatabaseRole [ADMIN]    Script Date: 21.02.2021 8:59:34 ******/
CREATE ROLE [ADMIN]
GO
ALTER ROLE [db_datareader] ADD MEMBER [horse_racing_employer]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [horse_racing_employer]
GO
/****** Object:  Table [dbo].[coach]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[coach](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
 CONSTRAINT [PK_coach] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[horse]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[horse](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[birth] [date] NOT NULL,
	[sex] [varchar](20) NOT NULL,
	[id_stud] [int] NOT NULL,
 CONSTRAINT [PK_horse] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ippo]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ippo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
 CONSTRAINT [PK_ippo] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[jokey]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[jokey](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NULL,
 CONSTRAINT [PK_jokey] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[player]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[player](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[first_n] [varchar](50) NOT NULL,
	[last_n] [varchar](50) NOT NULL,
	[login] [varchar](50) NULL,
	[password] [varchar](50) NULL,
 CONSTRAINT [PK_player] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[player_bet]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[player_bet](
	[id_player] [int] NOT NULL,
	[id_ippo] [int] NOT NULL,
	[date_ride] [date] NOT NULL,
	[num_ride] [int] NOT NULL,
	[id_horse] [int] NOT NULL,
	[id_type_bet] [int] NULL,
	[bet] [int] NULL,
	[payout] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[racing_map]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[racing_map](
	[id_ippo] [int] NOT NULL,
	[date_ride] [date] NOT NULL,
	[num_ride] [int] NOT NULL,
	[id_horse] [int] NOT NULL,
	[id_jokey] [int] NOT NULL,
	[id_coach] [int] NOT NULL,
	[weight] [int] NOT NULL,
	[last_ride] [date] NULL,
	[distance] [int] NULL,
	[rating] [numeric](5, 2) NULL,
	[prize_place] [int] NULL,
 CONSTRAINT [PK_racing_map] PRIMARY KEY CLUSTERED 
(
	[id_ippo] ASC,
	[date_ride] ASC,
	[num_ride] ASC,
	[id_horse] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[stud]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[stud](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
 CONSTRAINT [PK_studs_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[type_bet]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[type_bet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[type_bet] [varchar](100) NOT NULL,
	[rate] [decimal](5, 2) NOT NULL,
 CONSTRAINT [PK_type_bet] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[bets_of_player]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[bets_of_player]
AS
SELECT        dbo.player.id, dbo.player.last_n, dbo.player.first_n, dbo.player_bet.date_ride, dbo.player_bet.num_ride, dbo.horse.name AS horse, dbo.type_bet.type_bet, dbo.player_bet.bet AS [bet(roubles)], dbo.type_bet.rate, 
                         dbo.player_bet.payout
FROM            dbo.player INNER JOIN
                         dbo.player_bet ON dbo.player.id = dbo.player_bet.id_player INNER JOIN
                         dbo.horse ON dbo.player_bet.id_horse = dbo.horse.id INNER JOIN
                         dbo.type_bet ON dbo.player_bet.id_type_bet = dbo.type_bet.id
WHERE        (dbo.player.id = 41)
GO
/****** Object:  View [dbo].[coach_winner]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[coach_winner]
AS
SELECT        TOP (100) PERCENT dbo.coach.name AS coach, dbo.ippo.name AS ippodrom, dbo.racing_map.date_ride, dbo.jokey.name AS jokey, dbo.racing_map.prize_place
FROM            dbo.coach INNER JOIN
                         dbo.racing_map ON dbo.coach.id = dbo.racing_map.id_coach INNER JOIN
                         dbo.ippo ON dbo.racing_map.id_ippo = dbo.ippo.id INNER JOIN
                         dbo.jokey ON dbo.racing_map.id_jokey = dbo.jokey.id
WHERE        (dbo.coach.id = 1)
ORDER BY dbo.racing_map.prize_place
GO
/****** Object:  View [dbo].[jokey_winner]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[jokey_winner]
AS
SELECT        TOP (100) PERCENT dbo.jokey.name AS jokey, dbo.horse.name AS horse, dbo.ippo.name AS ippodrom, dbo.racing_map.date_ride, dbo.racing_map.distance, dbo.racing_map.prize_place
FROM            dbo.jokey INNER JOIN
                         dbo.racing_map ON dbo.jokey.id = dbo.racing_map.id_jokey INNER JOIN
                         dbo.horse ON dbo.racing_map.id_horse = dbo.horse.id INNER JOIN
                         dbo.ippo ON dbo.racing_map.id_ippo = dbo.ippo.id
WHERE        (dbo.horse.id = 1)
ORDER BY dbo.racing_map.prize_place
GO
/****** Object:  View [dbo].[racing_map_wide]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[racing_map_wide]
AS
SELECT        dbo.racing_map.id_ippo, dbo.ippo.name AS ippodrom, dbo.racing_map.date_ride, dbo.racing_map.num_ride, dbo.horse.id AS id_horse, dbo.horse.name AS Horse, dbo.horse.birth, dbo.horse.sex, 
                         dbo.racing_map.id_jokey AS Jokey, dbo.jokey.name, dbo.racing_map.id_coach AS Coach, dbo.racing_map.weight, dbo.racing_map.last_ride, dbo.racing_map.distance, dbo.racing_map.rating, dbo.racing_map.prize_place
FROM            dbo.racing_map INNER JOIN
                         dbo.ippo ON dbo.racing_map.id_ippo = dbo.ippo.id INNER JOIN
                         dbo.horse ON dbo.racing_map.id_horse = dbo.horse.id INNER JOIN
                         dbo.jokey ON dbo.racing_map.id_jokey = dbo.jokey.id INNER JOIN
                         dbo.coach ON dbo.racing_map.id_coach = dbo.coach.id
WHERE        (dbo.racing_map.id_ippo = 1)
GO
/****** Object:  View [dbo].[stud_winner]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[stud_winner]
WITH SCHEMABINDING 
AS
SELECT        TOP (100) PERCENT dbo.stud.name AS stud, dbo.horse.sex, dbo.horse.name AS horse, dbo.horse.birth, dbo.racing_map.prize_place
FROM            dbo.racing_map INNER JOIN
                         dbo.horse ON dbo.racing_map.id_horse = dbo.horse.id INNER JOIN
                         dbo.stud ON dbo.horse.id_stud = dbo.stud.id
WHERE        (dbo.stud.id = 2)
ORDER BY dbo.racing_map.prize_place, dbo.horse.birth
GO
/****** Object:  View [dbo].[to_payment]    Script Date: 21.02.2021 8:59:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[to_payment]
AS
SELECT        dbo.player.last_n AS surname, dbo.player.first_n AS name, dbo.racing_map.date_ride, dbo.racing_map.num_ride, dbo.horse.name AS horse, dbo.racing_map.prize_place, dbo.type_bet.type_bet, 
                         dbo.player_bet.bet AS [bet(roubles)], dbo.type_bet.rate, dbo.player_bet.payout, dbo.player.id
FROM            dbo.player_bet INNER JOIN
                         dbo.racing_map ON dbo.player_bet.id_ippo = dbo.racing_map.id_ippo AND dbo.player_bet.date_ride = dbo.racing_map.date_ride AND dbo.player_bet.num_ride = dbo.racing_map.num_ride AND 
                         dbo.player_bet.id_horse = dbo.racing_map.id_horse INNER JOIN
                         dbo.horse ON dbo.racing_map.id_horse = dbo.horse.id INNER JOIN
                         dbo.player ON dbo.player_bet.id_player = dbo.player.id INNER JOIN
                         dbo.type_bet ON dbo.player_bet.id_type_bet = dbo.type_bet.id
WHERE        (dbo.player.id = 42)
GO
/****** Object:  Index [player_bet]    Script Date: 21.02.2021 8:59:35 ******/
CREATE NONCLUSTERED INDEX [player_bet] ON [dbo].[player_bet]
(
	[id_player] ASC,
	[date_ride] ASC,
	[num_ride] ASC,
	[id_horse] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [player_bet_type_bet]    Script Date: 21.02.2021 8:59:35 ******/
CREATE NONCLUSTERED INDEX [player_bet_type_bet] ON [dbo].[player_bet]
(
	[id_type_bet] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [racing_map]    Script Date: 21.02.2021 8:59:35 ******/
CREATE NONCLUSTERED INDEX [racing_map] ON [dbo].[player_bet]
(
	[id_ippo] ASC,
	[date_ride] ASC,
	[num_ride] ASC,
	[id_horse] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_racing_map]    Script Date: 21.02.2021 8:59:35 ******/
CREATE NONCLUSTERED INDEX [IX_racing_map] ON [dbo].[racing_map]
(
	[id_ippo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[horse]  WITH CHECK ADD  CONSTRAINT [FK_horse_stud] FOREIGN KEY([id_stud])
REFERENCES [dbo].[stud] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[horse] CHECK CONSTRAINT [FK_horse_stud]
GO
ALTER TABLE [dbo].[player_bet]  WITH CHECK ADD  CONSTRAINT [FK_player_bet_player] FOREIGN KEY([id_player])
REFERENCES [dbo].[player] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[player_bet] CHECK CONSTRAINT [FK_player_bet_player]
GO
ALTER TABLE [dbo].[player_bet]  WITH CHECK ADD  CONSTRAINT [FK_player_bet_racing_map] FOREIGN KEY([id_ippo], [date_ride], [num_ride], [id_horse])
REFERENCES [dbo].[racing_map] ([id_ippo], [date_ride], [num_ride], [id_horse])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[player_bet] CHECK CONSTRAINT [FK_player_bet_racing_map]
GO
ALTER TABLE [dbo].[player_bet]  WITH CHECK ADD  CONSTRAINT [FK_player_bet_type_bet] FOREIGN KEY([id_type_bet])
REFERENCES [dbo].[type_bet] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[player_bet] CHECK CONSTRAINT [FK_player_bet_type_bet]
GO
ALTER TABLE [dbo].[racing_map]  WITH CHECK ADD  CONSTRAINT [FK_racing_map_coach] FOREIGN KEY([id_coach])
REFERENCES [dbo].[coach] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[racing_map] CHECK CONSTRAINT [FK_racing_map_coach]
GO
ALTER TABLE [dbo].[racing_map]  WITH CHECK ADD  CONSTRAINT [FK_racing_map_horse] FOREIGN KEY([id_horse])
REFERENCES [dbo].[horse] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[racing_map] CHECK CONSTRAINT [FK_racing_map_horse]
GO
ALTER TABLE [dbo].[racing_map]  WITH CHECK ADD  CONSTRAINT [FK_racing_map_ippo] FOREIGN KEY([id_ippo])
REFERENCES [dbo].[ippo] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[racing_map] CHECK CONSTRAINT [FK_racing_map_ippo]
GO
ALTER TABLE [dbo].[racing_map]  WITH CHECK ADD  CONSTRAINT [FK_racing_map_jokey] FOREIGN KEY([id_jokey])
REFERENCES [dbo].[jokey] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[racing_map] CHECK CONSTRAINT [FK_racing_map_jokey]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ставки, сделанные игроком' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'bets_of_player'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = -96
      End
      Begin Tables = 
         Begin Table = "player"
            Begin Extent = 
               Top = 6
               Left = 15
               Bottom = 136
               Right = 185
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "player_bet"
            Begin Extent = 
               Top = 6
               Left = 246
               Bottom = 221
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "horse"
            Begin Extent = 
               Top = 11
               Left = 472
               Bottom = 141
               Right = 642
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "type_bet"
            Begin Extent = 
               Top = 220
               Left = 469
               Bottom = 333
               Right = 639
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 12
         Width = 284
         Width = 1500
         Width = 1500
         Width = 3120
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 1530
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'bets_of_player'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'bets_of_player'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'bets_of_player'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Победы тренера' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'coach_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "coach"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 102
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "racing_map"
            Begin Extent = 
               Top = 6
               Left = 246
               Bottom = 250
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "ippo"
            Begin Extent = 
               Top = 6
               Left = 454
               Bottom = 102
               Right = 624
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "jokey"
            Begin Extent = 
               Top = 135
               Left = 23
               Bottom = 231
               Right = 193
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 2025
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'coach_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'coach_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'coach_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Победы жокея' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'jokey_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "jokey"
            Begin Extent = 
               Top = 147
               Left = 22
               Bottom = 243
               Right = 192
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "racing_map"
            Begin Extent = 
               Top = 6
               Left = 246
               Bottom = 136
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 7
         End
         Begin Table = "horse"
            Begin Extent = 
               Top = 6
               Left = 454
               Bottom = 136
               Right = 624
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "ippo"
            Begin Extent = 
               Top = 19
               Left = 26
               Bottom = 115
               Right = 196
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'jokey_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'jokey_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'jokey_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "racing_map"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 315
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "ippo"
            Begin Extent = 
               Top = 6
               Left = 246
               Bottom = 102
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "horse"
            Begin Extent = 
               Top = 102
               Left = 246
               Bottom = 232
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "jokey"
            Begin Extent = 
               Top = 232
               Left = 248
               Bottom = 328
               Right = 418
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "coach"
            Begin Extent = 
               Top = 366
               Left = 248
               Bottom = 462
               Right = 418
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 16
         Width = 284
         Width = 510
         Width = 2010
         Width = 1500
         Width = 810
         Width = 885
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'racing_map_wide'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'racing_map_wide'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'racing_map_wide'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Распределение победителей по заводам' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'stud_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "racing_map"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 7
         End
         Begin Table = "horse"
            Begin Extent = 
               Top = 6
               Left = 246
               Bottom = 136
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "stud"
            Begin Extent = 
               Top = 6
               Left = 454
               Bottom = 146
               Right = 624
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 3450
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 2280
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'stud_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'stud_winner'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Ставки и оплата игрока' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'to_payment'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = -19
      End
      Begin Tables = 
         Begin Table = "player_bet"
            Begin Extent = 
               Top = 180
               Left = 21
               Bottom = 405
               Right = 191
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "racing_map"
            Begin Extent = 
               Top = 111
               Left = 403
               Bottom = 376
               Right = 573
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "horse"
            Begin Extent = 
               Top = 102
               Left = 604
               Bottom = 232
               Right = 774
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "player"
            Begin Extent = 
               Top = 3
               Left = 223
               Bottom = 133
               Right = 393
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "type_bet"
            Begin Extent = 
               Top = 421
               Left = 235
               Bottom = 534
               Right = 405
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 11
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'to_payment'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'to_payment'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'to_payment'
GO
USE [master]
GO
ALTER DATABASE [HorseRacingTest] SET  READ_WRITE 
GO
