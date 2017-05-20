using System;
using System.Linq;

using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.DL.EFDataLayer.Tests
{
    [TestClass]
    public class EFDataAccessTests
    {
        [TestMethod]
        public void Add_NewCategory_SaveItemIntoDB()
        {
            // Arrange
            var categoryName = string.Format("Test Category - {0}", DateTime.Now.Ticks);
            var category = new Category()
            {
                Name = categoryName
            };

            using (var ctx = new WebShopContext())
            {
                var efDataAccess = new EFDataAccess<Category>(ctx);

                // Act
                efDataAccess.Add(category);
                ctx.SaveChanges();
            }

            // Assert
            using (var ctx = new WebShopContext())
            {
                var insertedCategory = ctx.Categories.Single(x => x.Name == categoryName);
            }
        }

        [TestMethod]
        public void Delete_ExistingCategory_CategoryGetsRemovedFromDB()
        { 
            // Arrange
            string categoryName = "";
            using (var ctx = new WebShopContext())
            {
                var category = ctx.Categories.First(x => x.Name.StartsWith("Test Category - "));
                categoryName = category.Name;
                var efDataAccess = new EFDataAccess<Category>(ctx);

                // Act
                efDataAccess.Delete(category);
                ctx.SaveChanges();
            }

            // Assert
            using (var ctx = new WebShopContext())
            {
                var deletedCategoryCount = ctx.Categories.Count(x => 
                    x.Name == categoryName);

                Assert.AreEqual(0, deletedCategoryCount, "Count of deleted categories is not 0");
            }
        }
    }
}
