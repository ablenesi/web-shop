using System;
using System.Linq;

using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebShop.DL.EFDataLayer;
using WebShop.IoC.DI;
using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerImpl.Tests
{
    [TestClass]
    public class CategoryOperationsTest
    {
        [TestMethod]
        public void Create_NewCategoryUnitOfWorkIOC_SaveIntoDB()
        {

            var category = new CategoryDTO
            {
                Name = String.Format("Test Category {0}", DateTime.Now.Ticks)
            };

            var unitOfWork = Resolver.Get<IUnitOfWork>();
            
            // Act
            unitOfWork.CategoryOperations.Create(category);
            unitOfWork.SaveChanges();

            // Assert
            using(var ctx = new WebShopContext())
            {
                var savedCategory = ctx.Categories.Single(x => x.Name == category.Name);
                Assert.IsNotNull(savedCategory);
            };
        }
    }
}
