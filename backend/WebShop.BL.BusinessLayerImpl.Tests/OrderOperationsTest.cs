using System;
using System.Collections.Generic;
using System.Linq;

using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebShop.DL.DataLayerContract.Entities;
using WebShop.DL.EFDataLayer;
using WebShop.IoC.DI;
using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerImpl.Tests
{
    [TestClass]
    public class OrderOperationsTest
    {
        [TestMethod]
        public void Create_NewOrderUsingUnitOfWorkAndIoC_SaveChangesIntoDB()
        {
            // Arrange
            Product product = null;
            var orderName = string.Format("Test Order - {0}", DateTime.Now.Ticks);
            var orderAddress = string.Format("Test Adress");
            
            using (var ctx = new WebShopContext())
            {
                product = ctx.Products.First();
            };

            var order = new OrderDTO
            {
                Name = orderName,
                Address = orderAddress,
                Date = DateTime.Now,
                OrderDetails = new List<OrderDetailDTO>{new OrderDetailDTO
                        {
                            ProductId = product.Id,
                            Quantity = 10
                        }
                    }
            };

            var unitOfWork = Resolver.Get<IUnitOfWork>();

            // Act
            unitOfWork.OrderOperations.Create(order);
            unitOfWork.SaveChanges();
            
            // Assert
            using (var ctx = new WebShopContext())
            {
                var savedProduct = ctx.Orders.Single(x => x.Name == orderName);
                Assert.AreEqual(orderAddress, savedProduct.Address, "The address is wrong.");
            }

        }

    }
}
