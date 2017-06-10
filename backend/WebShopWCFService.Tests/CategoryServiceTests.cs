using System;
using System.Linq;

using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebShop.DL.EFDataLayer;
using WebShop.SL.Client;
using WebShop.DL.DataLayerContract.Entities;
using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShopWCFService.Tests
{
    [TestClass]
    public class CategoryServiceTests
    {
        [TestMethod]
        public void Create_ViaServiceCall_CategoryGetsCreated()
        {
            // Arrange

            var client = new CategoryClient();
            var category = new CategoryDTO()
            {
                Name = "testCategory"
            };

            // Act
            client.Create(category);

            // Assert
            using (var ctx = new WebShopContext())
            {
                var createdCategory = ctx.Categories.First(x => x.Name == "testCategory");
            }
        }
    }
}
